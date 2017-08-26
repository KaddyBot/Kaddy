package kaddy.plugin.java

import com.typesafe.config.ConfigFactory
import kaddy.Kaddy
import kaddy.event.Event
import kaddy.event.EventException
import kaddy.event.EventHandler
import kaddy.event.Listener
import kaddy.plugin.*
import kaddy.util.fullName
import kaddy.util.use
import java.io.File
import java.io.FileNotFoundException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.HashSet
import java.util.Scanner
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.jar.JarFile

internal class JavaPluginLoader(internal val kaddy: Kaddy) : PluginLoader {

    companion object {
        const val FILE_PATTERN = """^.+\.jar$"""
    }

    private val classes = ConcurrentHashMap<String, Class<*>>()
    private val loaders = CopyOnWriteArrayList<PluginClassLoader>()

    override fun loadPlugin(file: File): Plugin {
        if (!file.exists()) {
            throw InvalidPluginException(cause = FileNotFoundException(file.path + " does not exist"))
        }

        val description: PluginDescriptionFile
        try {
            description = getPluginDescription(file)
        } catch (e: InvalidDescriptionException) {
            throw InvalidPluginException(cause = e)
        }


        val parentFile = file.parentFile
        val dataFolder = File(parentFile, description.name)

        if (dataFolder.exists() && !dataFolder.isDirectory) {
            throw InvalidPluginException("Projected datafolder: '$dataFolder' for ${description.fullName} ($file) " +
                    "exists and is not a directory")
        }

//        for (pluginName in description.getDepend()) {
//            val current = server.getPluginManager().getPlugin(pluginName) ?: throw UnknownDependencyException(pluginName)
//        }

        val loader: PluginClassLoader
        try {
            loader = PluginClassLoader(this, javaClass.classLoader, description, dataFolder, file)
        } catch (e: InvalidPluginException) {
            throw e
        } catch (e: Throwable) {
            throw InvalidPluginException(cause = e)
        }

        loaders.add(loader)

        return loader.plugin
    }

    @Throws(InvalidDescriptionException::class)
    override fun getPluginDescription(file: File): PluginDescriptionFile {
        try {
            JarFile(file).use { jar ->
                val entry = jar.getJarEntry("plugin.conf") ?: throw InvalidDescriptionException(
                        cause = FileNotFoundException("Jar does not contain plugin.conf"))
                jar.getInputStream(entry).use { stream ->
                    Scanner(stream).use { scanner ->
                        scanner.useDelimiter("""\A""")
                        val config = ConfigFactory.parseString(if (scanner.hasNext()) scanner.next() else "")
                        return PluginDescriptionFile(config)
                        //return config.extract<PluginDescriptionFile>("plugin")
                    }
                }
            }
        } catch (e: Exception) {
            throw InvalidDescriptionException(cause = e)
        }
    }

    override val pluginFileFilters: List<Regex> = listOf(FILE_PATTERN.toRegex())

    internal fun getClassByName(name: String): Class<*>? {
        var cachedClass: Class<*>? = classes[name]

        if (cachedClass != null) {
            return cachedClass
        } else {
            for (loader in loaders) {
                try {
                    cachedClass = loader.findClass(name, false)
                } catch (ignore: ClassNotFoundException) {  }

                if (cachedClass != null) {
                    return cachedClass
                }
            }
        }
        return null
    }

    internal fun setClass(name: String, clazz: Class<*>) {
        classes.putIfAbsent(name, clazz)
    }

    private fun removeClass(name: String) {
        classes.remove(name)
    }

    override fun enablePlugin(plugin: Plugin) {
        if (plugin !is JavaPlugin) throw IllegalArgumentException("Plugin is not associated with this PluginLoader")

        if (plugin.isNotEnabled) {
            plugin.logger.info { "Enabled ${plugin.description.fullName}" }

            val pluginLoader = plugin.classLoader as PluginClassLoader

            if (!loaders.contains(pluginLoader)) {
                loaders.add(pluginLoader)
                kaddy.logger.warn { "Enabled plugin with unregistered PluginClassLoader ${plugin.description.fullName}" }
            }

            try {
                plugin.enabled = true
            } catch (e: Throwable) {
                kaddy.logger.error(e) {
                    "Error occurred while enabling ${plugin.description.fullName} (Is it up to date?)"
                }
            }

            // TODO call listeners
        }
    }

    override fun disablePlugin(plugin: Plugin) {
        if (plugin !is JavaPlugin) throw IllegalArgumentException("Plugin is not associated with this PluginLoader")

        if (plugin.isEnabled) {
            plugin.logger.info { "Disabling ${plugin.description.fullName}" }

            // TODO call listeners

            val classLoader = plugin.classLoader

            try {
                plugin.enabled = false
            } catch (e: Throwable) {
                kaddy.logger.error(e) {
                    "Error occurred while disabling ${plugin.description.fullName} (Is it up to date?)"
                }
            }

            if (classLoader is PluginClassLoader) {
                loaders.remove(classLoader)

                for (name in classLoader.classes) {
                    removeClass(name)
                }
            }
        }
    }

    private fun getMethods(listenerClass: Class<out Listener>): Set<Method> {
        val methods: MutableSet<Method>

        val publicMethods = listenerClass.getMethods()
        val privateMethods = listenerClass.getDeclaredMethods()

        methods = HashSet(publicMethods.size + privateMethods.size, 1.0f)

        for (method in publicMethods) {
            methods.add(method)
        }
        for (method in privateMethods) {
            methods.add(method)
        }

        return methods
    }

    private fun checkMethod(method: Method, listenerClass: Class<out Listener>, plugin: Plugin): MethodHandler? {
        val eventHandler = method.getAnnotation(EventHandler::class.java) ?: return null

        if (method.isBridge || method.isSynthetic) {
            return null
        }

        val checkClass: Class<*>? = if (method.parameterTypes.size == 1) {
            method.parameterTypes[0]
        } else {
            null
        }
        if (checkClass == null || !Event::class.java.isAssignableFrom(checkClass)) {
            plugin.logger.error("${plugin.fullName} attempted to register an invalid EventHandler method " +
                    "signature \"${method.toGenericString()}\" in ${listenerClass}")
            return null
        }

        val eventClass = checkClass.asSubclass(Event::class.java)
        return MethodHandler(method, eventHandler, eventClass)
    }

    private class MethodHandler(val method: Method, val eventHandler: EventHandler, val eventClass: Class<out Event>) {
        init {
            method.isAccessible = true
        }
    }

    override fun createRegisteredListeners(listener: Listener, plugin: Plugin):
            Map<Class<out Event>,Set<RegisteredListener>> {

        val ret = mutableMapOf<Class<out Event>, MutableSet<RegisteredListener>>()
        val methods: Set<Method>

        val listenerClass = listener::class.java

        try {
            methods = getMethods(listenerClass)
        } catch (e: NoClassDefFoundError) {
            plugin.logger.error(e) { "Plugin ${plugin.fullName} has failed to register events for $listenerClass " +
                "because ${e.message} does not exist." }
            return ret
        }


        for (method in methods) {
            val mh = checkMethod(method, listenerClass, plugin) ?: continue

            var eventSet: MutableSet<RegisteredListener>? = ret[mh.eventClass]
            if (eventSet == null) {
                eventSet = HashSet()
                ret.put(mh.eventClass, eventSet)
            }

            var clazz: Class<*>? = mh.eventClass
            while (Event::class.java.isAssignableFrom(clazz)) {
                // This loop checks for extending deprecated events
                if (clazz?.getAnnotation(Deprecated::class.java) != null) {
                    plugin.logger.warn { "\"${plugin.fullName}\" has registered a listener for ${clazz?.name} on " +
                            "method \"${method.toGenericString()}\", but the event is Deprecated. Please notify the " +
                            "authors ${plugin.description.authors}." }
                    break
                }
                clazz = clazz?.superclass
            }

            val executor = object : EventExecutor {
                @Throws(EventException::class)
                override fun execute(listener: Listener, event: Event) {
                    try {
                        if (!mh.eventClass.isAssignableFrom(event::class.java)) {
                            return
                        }
                        method.invoke(listener, event)
                    } catch (e: InvocationTargetException) {
                        throw EventException(e.cause)
                    } catch (t: Throwable) {
                        throw EventException(t)
                    }

                }
            }
            val eh = mh.eventHandler
            eventSet.add(RegisteredListener(listener, executor, eh.priority, plugin, eh.ignoreCancelled))
        }
        return ret
    }
}
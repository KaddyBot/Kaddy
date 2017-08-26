package kaddy.plugin.java

import kaddy.plugin.InvalidPluginException
import kaddy.plugin.Plugin
import kaddy.plugin.PluginDescriptionFile
import kaddy.plugin.PluginLoader
import java.io.File
import java.lang.reflect.Constructor
import java.net.URLClassLoader
import java.util.concurrent.ConcurrentHashMap

internal class PluginClassLoader(private val loader: JavaPluginLoader,
                                 parent: ClassLoader,
                                 private val description: PluginDescriptionFile,
                                 dataFolder: File,
                                 file: File)
    : URLClassLoader(arrayOf(file.toURI().toURL()), parent) {

    val plugin: Plugin

    private var pluginInit: JavaPlugin? = null
    private var pluginState: IllegalStateException? = null
    private val classMap: MutableMap<String, Class<*>?> = ConcurrentHashMap()
    val classes: Set<String>
        get() = classMap.keys

    init {
        try {
            val jarClass: Class<*>
            try {
                jarClass = Class.forName(description.main, true, this)
            } catch (e: ClassNotFoundException) {
                throw InvalidPluginException("Cannot find main class '${description.main}'", e)
            }

            val pluginClass: Class<out JavaPlugin>
            try {
                pluginClass = jarClass.asSubclass(JavaPlugin::class.java)
            } catch (e: ClassCastException) {
                throw InvalidPluginException("main class '${description.main}' does not extend JavaPlugin", e)
            }


            plugin = pluginClass.newInstance()
        } catch (ex: IllegalAccessException) {
            throw InvalidPluginException("No public constructor", ex)
        } catch (ex: InstantiationException) {
            throw InvalidPluginException("Abnormal plugin type", ex)
        }
    }

    @Throws(ClassNotFoundException::class)
    override fun findClass(name: String): Class<*> {
        return findClass(name, true) ?: throw ClassNotFoundException(name)
    }

    @Throws(ClassNotFoundException::class)
    internal fun findClass(name: String, checkGlobal: Boolean): Class<*>? {
        if (name.startsWith("kaddy.")) {
            throw ClassNotFoundException(name)
        }
        var result: Class<*>? = classMap[name]

        if (result == null) {
            if (checkGlobal) {
                result = loader.getClassByName(name)
            }

            if (result == null) {
                result = super.findClass(name)

                if (result != null) {
                    loader.setClass(name, result)
                }
            }

            classMap.put(name, result)
        }

        return result
    }

    @Synchronized
    internal fun initialize(plugin: JavaPlugin) {
        if (plugin::class.java.classLoader !== this)
            throw IllegalArgumentException("Cannot initialize plugin outside of this class loader")
        if (this.pluginInit != null) {
            throw IllegalArgumentException("Plugin already initialized!", pluginState)
        }

        pluginState = IllegalStateException("Initial initialization")
        this.pluginInit = plugin

        plugin.init(this.loader.kaddy, loader, description)
    }
}
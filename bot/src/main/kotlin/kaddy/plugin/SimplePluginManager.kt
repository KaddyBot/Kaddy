package kaddy.plugin

import com.google.common.io.Files
import kaddy.Kaddy
import kaddy.util.Loggable
import kaddy.util.KaddyLoggable
import kaddy.util.logger
import java.io.File
import java.io.IOException
import java.lang.reflect.Constructor

class SimplePluginManager(private val kaddy: Kaddy) : PluginManager, Loggable by KaddyLoggable {

    private val updateDirectory = File("./updates")
    private val fileAssociations = mutableMapOf<Regex, PluginLoader>()
    private val plugins = mutableListOf<Plugin>()
    private val lookupNames = mutableMapOf<String, Plugin>()

    override fun registerInterface(loader: Class<out PluginLoader>) {
        val instance: PluginLoader

        if (PluginLoader::class.java.isAssignableFrom(loader)) {
            val constructor: Constructor<out PluginLoader>

            try {
                constructor = loader.getDeclaredConstructor(Kaddy::class.java)
                constructor.isAccessible = true
                instance = constructor.newInstance(kaddy)
            } catch (e: NoSuchMethodException) {
                val className = loader.name
                throw IllegalArgumentException("Class $className does not have a $className(Kaddy) constructor", e)
            } catch (e: Exception) {
                throw IllegalArgumentException("Unexpected exception ${e::class.java.name} while attempting to" +
                        " construct a new instance of ${loader.name}", e)
            }

        } else {
            throw IllegalArgumentException(String.format("Class %s does not implement interface PluginLoader", loader.name))
        }

        val patterns = instance.pluginFileFilters

        synchronized(this) {
            for (pattern in patterns) {
                fileAssociations.put(pattern, instance)
            }
        }
    }

    private fun checkUpdate(file: File) {
        if (!updateDirectory.isDirectory()) {
            return
        }

        val updateFile = File(updateDirectory, file.name)
        if (updateFile.isFile()) {
            try {
                Files.copy(updateFile, file)
                updateFile.delete()
            } catch (ignore: IOException) { }
        }
    }

    override fun loadPlugin(file: File): Plugin? {
        checkUpdate(file)

        val filters = fileAssociations.keys
        var result: Plugin? = null

        logger.info { filters }

        for (filter in filters) {
            val name = file.name

            logger.info { "$filter matches $name? ${filter.matches(name)}"}

            if (filter.matches(name)) {
                val loader = fileAssociations[filter]

                result = loader?.loadPlugin(file) ?: throw IllegalStateException("Unexpected missing PluginLoader")
            }
        }

        if (result != null) {
            plugins.add(result)
            lookupNames[result.description.name] = result
        }

        return result
    }

    override fun unloadPlugin(plugin: Plugin) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enablePlugin(plugin: Plugin) {
        if (!plugin.isEnabled) {
            try {
                plugin.pluginLoader.enablePlugin(plugin)
            } catch (e: Throwable) {
                logger.error(e) { "Error occurred (in the plugin loader) while enabling " +
                        "${plugin.description.fullName} (Is it up to date?)" }
            }
        }
    }

    override fun disablePlugin(plugin: Plugin) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
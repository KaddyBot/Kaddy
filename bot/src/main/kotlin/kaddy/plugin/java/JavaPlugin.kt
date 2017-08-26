package kaddy.plugin.java

import kaddy.Kaddy
import kaddy.plugin.Plugin
import kaddy.plugin.PluginDescriptionFile
import kaddy.plugin.PluginLoader
import kaddy.util.Logging
import mu.KLogger

abstract class JavaPlugin() : Plugin {

    override final lateinit var bot: Kaddy
        internal set

    override final lateinit var description: PluginDescriptionFile
        internal set

    override lateinit var logger: KLogger
        internal set

    internal var enabled = false
        set(value) {
            if (value) onEnable() else onDisable()
            field = value
        }
    override final val isEnabled
        get() = enabled
    override final val isNotEnabled
        get() = !isEnabled


    val classLoader = this::class.java.classLoader

    init {
        classLoader as PluginClassLoader
        classLoader.initialize(this)
    }

    override final lateinit var pluginLoader: PluginLoader
        internal set

    override fun onEnable() { }

    override fun onDisable() { }

    internal fun init(kaddy: Kaddy, loader: PluginLoader, descriptionFile: PluginDescriptionFile) {
        this.bot = kaddy
        this.pluginLoader = loader
        this.description = descriptionFile
        Logging.init(this::class.java, description.name)
        this.logger = Logging.getLogger(this)
    }
}
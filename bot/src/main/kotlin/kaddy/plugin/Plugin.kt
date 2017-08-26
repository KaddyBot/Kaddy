package kaddy.plugin

import kaddy.Kaddy
import kaddy.util.LogOwner
import mu.KLogger

interface Plugin : LogOwner {

    val bot: Kaddy
    val logger: KLogger
    val description: PluginDescriptionFile
    val pluginLoader: PluginLoader

    val isEnabled: Boolean
    val isNotEnabled
        get() = !isEnabled

    fun onEnable()
    fun onDisable()
}
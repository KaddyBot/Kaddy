package kaddy.plugin

import kaddy.util.LogOwner
import mu.KLogger

interface Plugin : LogOwner {

    val logger: KLogger
    val description: PluginDescriptionFile
    val pluginLoader: PluginLoader

    val isEnabled: Boolean
    val isNotEnabled
        get() = !isEnabled

    fun onEnable()
    fun onDisable()
}
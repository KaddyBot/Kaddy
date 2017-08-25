package kaddy.plugin

import java.io.File

interface PluginManager {

    fun loadPlugin(file: File): Plugin

    //fun loadPlugins(directory: File): List<Plugin>

    fun unloadPlugin(plugin: Plugin)
}
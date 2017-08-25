package kaddy.plugin

import java.io.File

interface PluginManager {

    /**
     * Registers the specified plugin loader
     *
     * @param loader Class name of the PluginLoader to register
     * @throws IllegalArgumentException Thrown when the given Class is not a valid PluginLoader
     */
    @Throws(IllegalArgumentException::class)
    fun registerInterface(loader: Class<out PluginLoader>)

    fun loadPlugin(file: File): Plugin?

    //fun loadPlugins(directory: File): List<Plugin>

    fun unloadPlugin(plugin: Plugin)

    /**
     * Enables the specified plugin.
     *
     * Attempting to enable a plugin that is already enabled will have no effect.
     *
     * @param plugin Plugin to enable.
     */
    fun enablePlugin(plugin: Plugin)

    /**
     * Disables the specified plugin.
     *
     * Attempting to disable a plugin that is not enabled will have no effect.
     *
     * @param plugin Plugin to disable.
     */
    fun disablePlugin(plugin: Plugin)
}
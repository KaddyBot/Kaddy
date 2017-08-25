package kaddy.plugin

import kaddy.event.Event
import kaddy.event.Listener
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

    /**
     * Calls an event with the given details.
     *
     * @param event Event object.
     * @throws IllegalStateException Thrown when an asynchronous event is fired from synchronous code.
     *
     * *Note: This is best-effort basis, and should not be used to test synchronized state. This is an indicator for
     * flawed flow logic.*
     */
    @Throws(IllegalStateException::class)
    fun callEvent(event: Event)

    /**
     * Registers all the events in the given listener class.
     *
     * @param listener Listener to register.
     * @param plugin Plugin to register.
     */
    fun registerEvents(listener: Listener, plugin: Plugin)
}
package kaddy.plugin

import kaddy.event.Event
import kaddy.event.Listener
import java.io.File

interface PluginLoader {

    /**
     * Loads the plugin contained in the specified file.
     *
     * @param file File to attempt to load.
     * @return Plugin that was contained in the specified file, or null if unsuccessful.
     * @throws InvalidPluginException Thrown when the specified file is not a plugin.
     */
    @Throws(InvalidPluginException::class)
    fun loadPlugin(file: File): Plugin

    /**
     * Loads a PluginDescriptionFile from the specified file.
     *
     * @param file File to attempt to load from.
     * @return A new PluginDescriptionFile loaded from the plugin.yml in the specified file.
     */
    @Throws(InvalidDescriptionException::class)
    fun getPluginDescription(file: File): PluginDescriptionFile

    /**
     * A list of the file filters that describe what type of files this plugin loader will load as plugins.
     */
    val pluginFileFilters: List<Regex>

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
     * Creates and returns registered listeners for the event classes used in
     * this listener
     *
     * @param listener The object that will handle the eventual call back
     * @param plugin The plugin to use when creating registered listeners
     * @return The registered listeners.
     */
    fun createRegisteredListeners(listener: Listener, plugin: Plugin): Map<Class<out Event>, Set<RegisteredListener>>
}
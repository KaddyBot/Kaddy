package kaddy.event

import com.github.plugkit.event.Event
import com.github.plugkit.plugin.PluginManager
import kaddy.Kaddy

/**
 * Represents an event for Kaddy.
 *
 * All events require a static method named getHandlerList() which returns the same [HandlerList] as [.getHandlers].
 *
 * @see PluginManager.callEvent
 * @see PluginManager.registerEvents
 */
abstract class Event : Event<Kaddy>() {
    internal fun call(kaddy: Kaddy) {
        kaddy.pluginManager.callEvent(this)
    }
}
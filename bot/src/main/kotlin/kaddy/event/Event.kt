package kaddy.event

import kaddy.Kaddy
import kaddy.plugin.PluginManager

/**
 * Represents an event.
 *
 * All events require a static method named getHandlerList() which returns the same [HandlerList] as [.getHandlers].
 *
 * @see PluginManager.callEvent
 * @see PluginManager.registerEvents
 */
abstract class Event {
    /**
     * A user-friendly identifier.
     *
     * By default, it is the event's [Class.getSimpleName].
     */
    open val eventName: String by lazy { this::class.java.simpleName }

    abstract val handlers: HandlerList

    /**
     * A convenience method for calling an event.
     */
    internal fun call(kaddy: Kaddy) {
        kaddy.pluginManager.callEvent(this)
    }

    enum class Result {

        /**
         * Deny the event. Depending on the event, the action indicated by the
         * event will either not take place or will be reverted. Some actions
         * may not be denied.
         */
        DENY,
        /**
         * Neither deny nor allow the event. The server will proceed with its
         * normal handling.
         */
        DEFAULT,
        /**
         * Allow / Force the event. The action indicated by the event will
         * take place if possible, even if the server would not normally allow
         * the action. Some actions may not be allowed.
         */
        ALLOW
    }
}
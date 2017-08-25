package kaddy.plugin

import kaddy.event.Event
import kaddy.event.EventException
import kaddy.event.EventPriority
import kaddy.event.Listener

/**
 * Stores relevant information for plugin listeners
 */
class RegisteredListener(
        /**
         * Gets the listener for this registration
         *
         * @return Registered Listener
         */
        val listener: Listener, private val executor: EventExecutor,
        /**
         * Gets the priority for this registration
         *
         * @return Registered Priority
         */
        val priority: EventPriority,
        /**
         * Gets the plugin for this registration
         *
         * @return Registered Plugin
         */
        val plugin: Plugin,
        /**
         * Whether this listener accepts cancelled events
         *
         * @return True when ignoring cancelled events
         */
        val isIgnoringCancelled: Boolean) {

    /**
     * Calls the event executor
     *
     * @param event The event
     * @throws EventException If an event handler throws an exception.
     */
    @Throws(EventException::class)
    fun callEvent(event: Event) {
//        if (event is Cancellable) {
//            if ((event as Cancellable).isCancelled() && isIgnoringCancelled) {
//                return
//            }
//        }
        executor.execute(listener, event)
    }
}

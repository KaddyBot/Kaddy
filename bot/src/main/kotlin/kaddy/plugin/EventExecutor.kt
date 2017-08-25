package kaddy.plugin

import kaddy.event.Event
import kaddy.event.EventException
import kaddy.event.Listener

/**
 * Interface which defines the class for event call backs to plugins
 */
interface EventExecutor {
    @Throws(EventException::class)
    fun execute(listener: Listener, event: Event)
}
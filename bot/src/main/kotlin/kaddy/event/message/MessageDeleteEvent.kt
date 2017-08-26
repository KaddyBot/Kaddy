package kaddy.event.message

import de.btobastian.javacord.entities.message.Message
import kaddy.event.HandlerList

/**
 * Indicates a message has been deleted.
 */
open class MessageDeleteEvent(message: Message) : MessageEvent(message) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
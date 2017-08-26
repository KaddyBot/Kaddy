package kaddy.event.message

import de.btobastian.javacord.entities.message.Message
import kaddy.event.HandlerList

/**
 * Indicates a message has been edited.
 */
open class MessageEditEvent(message: Message, val previousContent: String) : MessageEvent(message) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
package kaddy.event.message

import de.btobastian.javacord.entities.Channel
import de.btobastian.javacord.entities.User
import kaddy.event.Event
import kaddy.event.HandlerList

/**
 * Indicates a message is being typed by a user.
 */
open class TypingStartEvent(
        /**
         * The user that is typing.
         */
        val user: User,
        /**
         * The channel being typed in.
         */
        val channel: Channel) : Event() {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
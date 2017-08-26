package kaddy.event.message

import de.btobastian.javacord.entities.message.Message
import de.btobastian.javacord.entities.message.Reaction
import kaddy.event.HandlerList

/**
 * Indicates all reactions have been removed from a message.
 */
open class ReactionRemoveAllEvent(message: Message, previousReactions: List<Reaction>) : MessageEvent(message) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
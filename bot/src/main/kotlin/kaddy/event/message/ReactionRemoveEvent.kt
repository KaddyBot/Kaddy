package kaddy.event.message

import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.message.Reaction
import kaddy.event.HandlerList

/**
 * Indicates a reaction has been removed from a message.
 */
open class ReactionRemoveEvent(reaction: Reaction, user: User) : ReactionEvent(reaction, user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
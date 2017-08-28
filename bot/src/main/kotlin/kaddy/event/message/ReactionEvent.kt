package kaddy.event.message

import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.message.Reaction
import kaddy.event.Event

/**
 * For events related to reactions.
 */
abstract class ReactionEvent(
        /**
         * The reaction this event pertains to.
         */
        open val reaction: Reaction?,
        /**
         * The user responsible for the reaction.
         */
        open val user: User) : Event()
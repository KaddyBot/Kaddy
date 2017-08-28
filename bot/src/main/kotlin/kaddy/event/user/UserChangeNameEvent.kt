package kaddy.event.user

import de.btobastian.javacord.entities.User
import kaddy.event.HandlerList

/**
 * Indicates a user's name was changed.
 */
open class UserChangeNameEvent(user: User, val previousName: String) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
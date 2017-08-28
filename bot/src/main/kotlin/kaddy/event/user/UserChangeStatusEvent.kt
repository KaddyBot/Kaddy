package kaddy.event.user

import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.UserStatus
import kaddy.event.HandlerList

/**
 * Indicates a user's status was changed.
 */
open class UserChangeStatusEvent(user: User, val previousStatus: UserStatus) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
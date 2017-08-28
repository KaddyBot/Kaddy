package kaddy.event.user

import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role was removed from a user.
 */
open class UserRoleRemoveEvent(user: User, val addedRole: Role) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
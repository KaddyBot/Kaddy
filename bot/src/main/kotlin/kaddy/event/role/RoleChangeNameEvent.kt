package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role's name has been changed.
 */
open class RoleChangeNameEvent(role: Role, val previousName: String) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
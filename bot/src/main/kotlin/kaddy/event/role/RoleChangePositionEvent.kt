package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role's position has been changed.
 */
open class RoleChangePositionEvent(role: Role, val previousPosition: Int) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
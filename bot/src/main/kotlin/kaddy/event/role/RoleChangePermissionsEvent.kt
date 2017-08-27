package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Permissions
import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role's permissions has been changed.
 */
open class RoleChangePermissionsEvent(role: Role, val previousPermissions: Permissions) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
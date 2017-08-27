package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role became managed or unmanaged.
 *
 * Note: roles can be managed by an integration.
 */
open class RoleChangeManagedEvent(role: Role, val previouslyManaged: Boolean) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role became hoisted or unhoisted (added or removed from the sidebar).
 */
open class RoleChangeHoistedEvent(role: Role, val previouslyHoisted: Boolean) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
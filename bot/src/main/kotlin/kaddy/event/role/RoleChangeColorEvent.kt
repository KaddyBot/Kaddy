package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList
import java.awt.Color

/**
 * Indicates a role has changed color.
 */
open class RoleChangeColorEvent(role: Role, val previousColor: Color) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
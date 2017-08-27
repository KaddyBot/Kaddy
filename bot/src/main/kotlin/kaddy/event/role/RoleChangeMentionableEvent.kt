package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role became mentionable or unmentionable.
 */
open class RoleChangeMentionableEvent(role: Role, val previouslyMentionable: Boolean) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
package kaddy.event.server

import de.btobastian.javacord.entities.CustomEmoji
import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.Event
import kaddy.event.HandlerList
import kaddy.event.role.RoleEvent

/**
 * Indicates a custom emoji was deleted.
 */
open class CustomEmojiDeleteEvent(deletedEmoji: CustomEmoji) : Event() {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
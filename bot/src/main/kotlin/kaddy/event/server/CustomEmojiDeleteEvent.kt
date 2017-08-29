package kaddy.event.server

import de.btobastian.javacord.entities.CustomEmoji
import kaddy.event.HandlerList
import kaddy.event.Event

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
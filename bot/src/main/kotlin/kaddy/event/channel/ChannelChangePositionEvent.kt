package kaddy.event.channel

import de.btobastian.javacord.entities.Channel
import kaddy.event.HandlerList

/**
 * Indicates a channel's position has changed.
 */
open class ChannelChangePositionEvent(channel: Channel, val previousPosition: Int) : ChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
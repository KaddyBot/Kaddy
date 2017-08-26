package kaddy.event.channel

import de.btobastian.javacord.entities.Channel
import kaddy.event.HandlerList

/**
 * Indicates a channel's name has changed.
 */
open class ChannelChangeNameEvent(channel: Channel, val previousName: String) : ChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
package kaddy.event.channel

import de.btobastian.javacord.entities.Channel
import kaddy.event.HandlerList

/**
 * Indicates a channel has been created.
 */
open class ChannelCreateEvent(channel: Channel) : ChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
package kaddy.event.channel

import de.btobastian.javacord.entities.Channel
import kaddy.event.HandlerList

/**
 * Indicates a channel's topic has changed.
 */
open class ChannelChangeTopicEvent(channel: Channel, val previousTopic: String?) : ChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
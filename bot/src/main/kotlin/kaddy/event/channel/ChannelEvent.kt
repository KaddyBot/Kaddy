package kaddy.event.channel

import de.btobastian.javacord.entities.Channel
import kaddy.event.Event

/**
 * For events related to channels.
 */
abstract class ChannelEvent(
        /**
         * The channel this event pertains to.
         */
        val channel: Channel) : Event()
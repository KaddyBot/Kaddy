package kaddy.event.voicechannel

import de.btobastian.javacord.entities.VoiceChannel
import kaddy.event.HandlerList

/**
 * Indicates a voice channel's position has changed.
 */
open class VoiceChannelChangePositionEvent(channel: VoiceChannel, val previousPosition: Int) : VoiceChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
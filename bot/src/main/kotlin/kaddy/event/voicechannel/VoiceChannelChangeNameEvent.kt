package kaddy.event.voicechannel

import de.btobastian.javacord.entities.VoiceChannel
import kaddy.event.HandlerList

/**
 * Indicates a voice channel's name has changed.
 */
open class VoiceChannelChangeNameEvent(channel: VoiceChannel, val previousName: String) : VoiceChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
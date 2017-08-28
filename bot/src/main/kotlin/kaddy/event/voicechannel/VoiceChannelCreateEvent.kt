package kaddy.event.voicechannel

import de.btobastian.javacord.entities.VoiceChannel
import kaddy.event.HandlerList

/**
 * Indicates a voice channel was created.
 */
open class VoiceChannelCreateEvent(channel: VoiceChannel) : VoiceChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
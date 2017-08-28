package kaddy.event.voicechannel

import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.VoiceChannel
import kaddy.event.HandlerList

/**
 * Indicates a user joined a voice channel.
 */
open class UserJoinVoiceChannelEvent(channel: VoiceChannel, val user: User?) : VoiceChannelEvent(channel) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
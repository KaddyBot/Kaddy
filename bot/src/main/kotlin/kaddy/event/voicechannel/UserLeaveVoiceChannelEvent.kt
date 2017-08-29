package kaddy.event.voicechannel

import de.btobastian.javacord.entities.User
import kaddy.event.HandlerList
import kaddy.event.Event

/**
 * Indicates a user left a voice channel.
 */
open class UserLeaveVoiceChannelEvent(val user: User?) : Event() {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
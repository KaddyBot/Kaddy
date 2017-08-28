package kaddy.event.user

import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.VoiceChannel
import de.btobastian.javacord.entities.permissions.Permissions
import kaddy.event.HandlerList

/**
 * Indicates a user's overwritten permissions were changed in a voice channel
 */
open class UserChangeOverwrittenVoicePermissionsEvent(user: User, val channel: VoiceChannel,
                                                      val previousPermissions: Permissions) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
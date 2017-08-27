package kaddy.event.role

import de.btobastian.javacord.entities.Channel
import de.btobastian.javacord.entities.VoiceChannel
import de.btobastian.javacord.entities.permissions.Permissions
import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.HandlerList

/**
 * Indicates a role's overwritten permissions have been changed in a voice channel.
 */
open class RoleChangeOverwrittenPermissionsVoiceEvent(role: Role, val channel: VoiceChannel,
                                                      val previousPermissions: Permissions) : RoleEvent(role) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
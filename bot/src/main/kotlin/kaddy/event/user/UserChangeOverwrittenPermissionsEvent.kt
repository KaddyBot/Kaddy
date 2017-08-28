package kaddy.event.user

import de.btobastian.javacord.entities.Channel
import de.btobastian.javacord.entities.User
import de.btobastian.javacord.entities.permissions.Permissions
import kaddy.event.HandlerList

/**
 * Indicates a user's overwritten permissions were changed.
 */
open class UserChangeOverwrittenPermissionsEvent(user: User, val channel: Channel,
                                                 val previousPermissions: Permissions) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
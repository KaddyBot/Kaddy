package kaddy.event.user

import de.btobastian.javacord.entities.Server
import de.btobastian.javacord.entities.User
import kaddy.event.HandlerList

/**
 * Indicates a user's nickname was changed.
 */
open class UserChangeNicknameEvent(user: User, val server: Server, val previousNickname: String?) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
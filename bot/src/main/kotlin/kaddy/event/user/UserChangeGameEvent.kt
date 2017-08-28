package kaddy.event.user

import de.btobastian.javacord.entities.User
import kaddy.event.HandlerList

/**
 * Indicates a user's game was changed.
 */
open class UserChangeGameEvent(user: User, val previousGame: String?) : UserEvent(user) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
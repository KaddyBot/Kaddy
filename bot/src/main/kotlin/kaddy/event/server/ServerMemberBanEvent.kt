package kaddy.event.server

import de.btobastian.javacord.entities.Server
import de.btobastian.javacord.entities.User
import kaddy.event.HandlerList

/**
 * Indicates a user was banned from a server.
 */
open class ServerMemberBanEvent(server: Server, val user: User) : ServerEvent(server) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
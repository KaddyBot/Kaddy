package kaddy.event.server

import de.btobastian.javacord.entities.Server
import kaddy.event.HandlerList

/**
 * Indicates the bot joined a server.
 */
open class ServerJoinEvent(server: Server) : ServerEvent(server) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
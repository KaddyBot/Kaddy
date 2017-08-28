package kaddy.event.server

import de.btobastian.javacord.entities.Server
import kaddy.event.HandlerList

/**
 * Indicates a server's owner was changed.
 */
open class ServerChangeOwnerEvent(server: Server, val previousOwnerId: String) : ServerEvent(server) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
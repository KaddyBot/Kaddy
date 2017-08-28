package kaddy.event.server

import de.btobastian.javacord.entities.Server
import kaddy.event.HandlerList

/**
 * Indicates a server's name was changed.
 */
open class ServerChangeNameEvent(server: Server, val previousName: String) : ServerEvent(server) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
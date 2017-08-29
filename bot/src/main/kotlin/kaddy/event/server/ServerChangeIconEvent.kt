package kaddy.event.server

import de.btobastian.javacord.entities.Server
import kaddy.event.HandlerList

/**
 * Indicates a server's icon was changed.
 */
open class ServerChangeIconEvent(server: Server, val previousIcon: String?) : ServerEvent(server) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
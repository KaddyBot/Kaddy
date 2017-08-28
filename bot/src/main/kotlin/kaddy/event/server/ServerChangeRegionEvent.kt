package kaddy.event.server

import de.btobastian.javacord.entities.Region
import de.btobastian.javacord.entities.Server
import kaddy.event.HandlerList

/**
 * Indicates a server's region was changed.
 */
open class ServerChangeRegionEvent(server: Server, val previousRegion: Region) : ServerEvent(server) {

    override val handlers: HandlerList = h

    companion object {
        private val h = HandlerList()

        @JvmStatic
        fun getHandlerList() = h
    }
}
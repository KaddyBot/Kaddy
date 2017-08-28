package kaddy.event.server

import de.btobastian.javacord.entities.Server
import kaddy.event.Event

abstract class ServerEvent(open val server: Server) : Event()
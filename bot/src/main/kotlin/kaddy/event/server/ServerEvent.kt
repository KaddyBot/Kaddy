package kaddy.event.server

import de.btobastian.javacord.entities.Server
import kaddy.event.Event

abstract class ServerEvent(val server: Server) : Event()
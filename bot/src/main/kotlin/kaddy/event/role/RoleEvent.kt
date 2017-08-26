package kaddy.event.role

import de.btobastian.javacord.entities.permissions.Role
import kaddy.event.Event

abstract class RoleEvent(val role: Role) : Event()
package kaddy.event.user

import de.btobastian.javacord.entities.User
import kaddy.event.Event

abstract class UserEvent(val user: User) : Event()
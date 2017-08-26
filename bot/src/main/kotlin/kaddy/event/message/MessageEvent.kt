package kaddy.event.message

import de.btobastian.javacord.entities.message.Message
import kaddy.event.Event

abstract class MessageEvent(val message: Message) : Event()
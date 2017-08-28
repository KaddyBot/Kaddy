package kaddy.event.message

import de.btobastian.javacord.entities.message.Message
import kaddy.event.Event

/**
 * For events related to messages.
 */
abstract class MessageEvent(
        /**
         * The message this event pertains to.
         */
        open val message: Message) : Event()
/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2017  Kaddy Team
 *
 * Kaddy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kaddy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kaddy.  If not, see <http://www.gnu.org/licenses/>.
 */
package kaddy.util

import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.entities.MessageEmbed
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

inline fun <T : AutoCloseable, R> T.use(block: (T) -> R): R {
    var closed = false
    try {
        return block(this)
    } catch (e: Exception) {
        closed = true
        try {
            close()
        } catch (closeException: Exception) {
            e.addSuppressed(closeException)
        }
        throw e
    } finally {
        if (!closed) {
            close()
        }
    }
}

fun MessageChannel.queueMessage(message: CharSequence) {
    this.sendMessage(message).queue()
}

fun MessageChannel.queueMessage(message: Message) {
    this.sendMessage(message).queue()
}

fun MessageChannel.queueMessage(message: MessageEmbed) {
    this.sendMessage(message).queue()
}

fun MessageReceivedEvent.queueReply(message: CharSequence) {
    this.channel.sendMessage(message).queue()
}

fun MessageReceivedEvent.queueReply(message: Message) {
    this.channel.sendMessage(message).queue()
}

fun MessageReceivedEvent.queueReply(message: MessageEmbed) {
    this.channel.sendMessage(message).queue()
}
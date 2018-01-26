/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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

fun MessageChannel?.queueMessage(message: CharSequence) {
    this?.sendMessage(message)?.queue()
}

fun MessageChannel?.queueMessage(message: Message) {
    this?.sendMessage(message)?.queue()
}

fun MessageChannel?.queueMessage(message: MessageEmbed) {
    this?.sendMessage(message)?.queue()
}

fun MessageChannel?.completeMessage(message: CharSequence) {
    this?.sendMessage(message)?.complete()
}

fun MessageChannel?.completeMessage(message: Message) {
    this?.sendMessage(message)?.complete()
}

fun MessageChannel?.completeMessage(message: MessageEmbed) {
    this?.sendMessage(message)?.complete()
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
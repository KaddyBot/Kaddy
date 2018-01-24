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
package kaddy.listeners

import dtmlibs.config.datasource.DataHandlingException
import kaddy.KaddyBot
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.io.IOException
import java.nio.file.Files

internal class GeneralListener(private val bot: KaddyBot) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
       /* if (event.message.contentRaw == "${bot.config.defaultCommandPrefix}update" && event.author.idLong == 118330468025237505) {
//            event.channel.sendMessage("Attempting to update...").queue()
//            bot.attemptUpdate(event.channel)
        } else */if (event.message.contentRaw == "${bot.config.defaultCommandPrefix}stop" && event.author.idLong == 118330468025237505) {
            try {
                Files.createFile(KaddyBot.botStopPath)
            } catch (e: IOException) {
                bot.home?.sendMessage("Couldn't create shutdown file!")?.complete()
            } catch (e: java.nio.file.FileAlreadyExistsException) {
                bot.home?.sendMessage("Shutdown file already exists!")?.complete()
            }
            bot.disconnect()
        } else if (event.message.contentRaw == "${bot.config.defaultCommandPrefix}ping") {
            println("pong")
            event.channel.sendMessage("pong").queue()
        } else if (event.message.contentRaw == "${bot.config.defaultCommandPrefix}reload") {
            try {
                bot.config.load()
            } catch (e: DataHandlingException) {
                e.printStackTrace()
                event.channel.sendMessage("There was an error reloading the config: ${e.message}")
            }
            event.channel.sendMessage("Reloaded configuration.").queue()
        }
    }
}
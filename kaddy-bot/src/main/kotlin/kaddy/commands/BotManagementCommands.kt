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
package kaddy.commands

import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Conditions
import co.aikar.commands.annotation.Subcommand
import dtmlibs.config.datasource.DataHandlingException
import dtmlibs.logging.logger
import kaddy.KaddyBot
import kaddy.data.GuildData
import kaddy.util.queueReply
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.io.IOException
import java.nio.file.Files

@CommandAlias("bot")
class BotManagementCommands(bot: KaddyBot) : KaddyBaseCommand(bot) {

    @Subcommand("update")
    @CommandAlias("update")
    @Conditions("owneronly")
    fun update(event: MessageReceivedEvent) {
        bot.attemptUpdate(event.channel)
    }

    @Subcommand("shutdown|stop")
    @Conditions("owneronly")
    fun shutdown(event: MessageReceivedEvent) {
        event.channel.sendMessage("Shutting down...").queue()
        try {
            Files.createFile(KaddyBot.botStopPath)
        } catch (e: IOException) {
            event.channel.sendMessage("Couldn't create shutdown file!").queue()
        } catch (e: java.nio.file.FileAlreadyExistsException) {
            bot.home?.sendMessage("Shutdown file already exists!")?.queue()
        }
        bot.disconnect()
    }

    @Subcommand("restart")
    @Conditions("owneronly")
    fun restart(event: MessageReceivedEvent) {
        event.queueReply("Restarting...")
        bot.disconnect()
    }

    @Subcommand("reload|reloadconfig")
    @Conditions("owneronly")
    fun reload(event: MessageReceivedEvent) {
        try {
            bot.config.load()
        } catch (e: DataHandlingException) {
            e.printStackTrace()
            event.channel.sendMessage("There was an error reloading the config: ${e.message}").queue()
        }
        event.channel.sendMessage("Reloaded configuration.").queue()
    }

    @Subcommand("setprefix|prefix")
    @Conditions("owneronly|guildonly")
    fun setPrefix(event: MessageReceivedEvent, prefix: String) {
        try {
            GuildData.Companion.forGuild(event.guild).commandPrefix = prefix
            event.queueReply("I have changed the command prefix to `$prefix` for this guild.")
        } catch (e: IllegalArgumentException) {
            event.queueReply("Sorry, the max length for a prefix is ${GuildData.MAX_PREFIX_LENGTH} characters.")
        }

    }

    @Subcommand("ping")
    @CommandAlias("ping")
    fun ping(event: MessageReceivedEvent) {
        logger.info("Received ping command")
        event.channel.sendMessage("pong").queue()
    }
}
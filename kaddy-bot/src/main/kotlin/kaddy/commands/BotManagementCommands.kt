/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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

    @Subcommand("invite|invitelink")
    @Conditions("owneronly")
    fun inviteLink(event: MessageReceivedEvent) {
        event.author.openPrivateChannel().queue({
            it.sendMessage(bot.discordAPI.asBot().getInviteUrl()).queue()
        })
    }
}
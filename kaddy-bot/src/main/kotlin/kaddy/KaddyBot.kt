/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy

import ch.qos.logback.classic.Level
import co.aikar.commands.JDACommandManager
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import dtmlibs.config.datasource.DataHandlingException
import dtmlibs.logging.Logging
import dtmlibs.logging.logback.setRootLogLevel
import dtmlibs.logging.logger
import kaddy.commands.BotManagementCommands
import kaddy.data.Tables
import kaddy.util.completeMessage
import kaddy.util.queueMessage
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.hooks.EventListener
import org.jetbrains.exposed.sql.Database
import org.pf4j.DefaultPluginManager
import org.pf4j.PluginManager
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Scanner

class KaddyBot private constructor (internal val discordAPI: JDA, val config: Config)
    : Kaddy by KaddyImpl(discordAPI), EventListener {

    private class BotArgs(parser: ArgParser) {
        val devMode by parser.flagging("-d", "--dev-mode",
                help = "enable developer mode for IDEs");

        val token by parser.positional("TOKEN", "the bot's api token")
                .default(null)
    }

    companion object {
        private lateinit var bot: KaddyBot
        val config: Config by lazy { bot.config }

        internal val botStopPath: Path = Paths.get("./.bot-stop");
        internal val windows = System.getProperty("os.name").startsWith("Windows")

        @JvmStatic
        fun main(args: Array<String>) {
            val botArgs = BotArgs(ArgParser(args));

            if (botArgs.token == null) {
                println("Missing token.");
                return;
            }

            val config = Config()
            try {
                config.load()
            } catch (e: DataHandlingException) {
                logger.error { "Could not load config file." }
                e.printStackTrace()
            }

            Database.connect("jdbc:h2:file:${config.configDir}/database", "org.h2.Driver")
            Tables.createIfNonExistent()

            bot = KaddyBot(JDABuilder(AccountType.BOT).setToken(botArgs.token).buildBlocking(), config)

            if (botArgs.devMode) {
                val input = Scanner(System.`in`)

                while (true) {
                    val next = input.next()
                    if (next == "stop") {
                        stop();
                        break;
                    }
                }
            }
        }

        @JvmStatic
        fun stop() {
            bot.disconnect()
        }
    }

    val pluginManager: PluginManager = DefaultPluginManager()

    val home by lazy {
        textChannels[352502441838903296]
    }

    val commandManager: JDACommandManager by lazy {
        JDACommandManager(discordAPI, config, KaddyCommandConfigProvider(), null)
    }

    init {
        Logging.setRootLogLevel(Level.TRACE)
    }

    override fun onEvent(event: Event?) {
        if (event is ReadyEvent) {
            botReady()
        }
    }

    private fun botReady() {
        validateLastShutdown()
        registerCommands()

        pluginManager.loadPlugins()
        pluginManager.startPlugins()

        home.queueMessage("Hello!")
    }

    private fun validateLastShutdown() {
        if (Files.exists(botStopPath)) {
            home.queueMessage("The shutdown file was present when I started. This means I probably wasn't " +
                    "suppose to be started.")
            try {
                Files.delete(botStopPath)
            } catch (e: Exception) {
                e.printStackTrace()
                home.queueMessage("I couldn't delete the shutdown file!")
            }
        }
    }

    private fun registerCommands() {
        commandManager.registerCommand(BotManagementCommands(this))
    }

    internal fun disconnect() {
        pluginManager.stopPlugins()
        home.completeMessage("Good bye!")
        discordAPI.shutdown()
    }

    internal fun attemptUpdate(channel: MessageChannel) {
        logger.info("Attempting update...")
        channel.sendMessage("Attemping update...").queue()
        Thread({
            channel.sendMessage("Pulling changes...").queue()
            val updateProcess = ProcessBuilder().command("git", "pull").start()
            if (updateProcess.waitFor() == 0) {
                logger.info("Successfully pulled.")
            } else {
                channel.sendMessage("Couldn't pull.").queue()
                logger.error("Could not pull.")
                return@Thread
            }
            channel.sendMessage("Building...").queue()
            val buildProcess = ProcessBuilder().command(if (windows) "gradlew.bat" else "./gradlew", "clean", "build").start()
            if (buildProcess.waitFor() == 0) {
                logger.info("Successfully built.")
            } else {
                channel.sendMessage("Could not build.").queue()
                logger.error("Could not build.")
                return@Thread
            }
            channel.sendMessage("Restarting...").complete()
            logger.info("Restarting...")
            disconnect()
        }).start()
    }
}
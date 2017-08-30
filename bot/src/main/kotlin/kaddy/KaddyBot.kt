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
package kaddy

import ch.qos.logback.classic.Level
import de.btobastian.javacord.ImplDiscordAPI
import de.btobastian.javacord.Javacord
import dtmlibs.logging.Logging
import dtmlibs.logging.logback.setRootLogLevel
import kaddy.plugin.JarPluginLoader
import java.io.File
import java.util.Scanner

class KaddyBot private constructor (private val discordAPI: ImplDiscordAPI) : Kaddy by KaddyImpl(discordAPI) {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val bot = KaddyBot(Javacord.getApi(args[0], true) as ImplDiscordAPI)

            bot.connect()

            val input = Scanner(System.`in`)
            while (true) {
                val next = input.next()
                if (next == "stop") {
                    bot.disconnect()
                    System.exit(0)
                }
            }
        }
    }

    init {
        Logging.setRootLogLevel(Level.TRACE)
    }

    private fun connect() {
        logger.info { "Connecting bot..." }
        discordAPI.connectBlocking()
        logger.info { "Bot connected to Discord!" }
        discordAPI.registerListener(AllListener(this))
        try {
            pluginManager.registerInterface(JarPluginLoader::class.java)
            val p = pluginManager.loadPlugin(File("./plugins/sample-plugin.jar"))
            pluginManager.enablePlugin(p ?: throw IllegalArgumentException())
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun disconnect() {
        discordAPI.disconnect()
    }
}
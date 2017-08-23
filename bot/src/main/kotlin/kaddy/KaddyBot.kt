package kaddy

import ch.qos.logback.classic.Level
import de.btobastian.javacord.Javacord
import kaddy.util.LogOwner
import kaddy.util.Logging
import kaddy.util.logger
import java.util.Scanner

class KaddyBot(token: String) : LogOwner {

    companion object {

        init {
            Logging.init(KaddyBot::class.java, "Kaddy")
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val bot = KaddyBot(args[0])

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


    val discordAPI = DiscordAPI(Javacord.getApi(token, true))

    fun connect() {
        logger.info { "Connecting bot..." }
        discordAPI.internalAPI.connectBlocking()
        logger.info { "Bot connected to Discord!" }
    }

    fun disconnect() {
        discordAPI.internalAPI.disconnect()
    }
}
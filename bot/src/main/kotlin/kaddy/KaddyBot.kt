package kaddy

import ch.qos.logback.classic.Level
import de.btobastian.javacord.ImplDiscordAPI
import de.btobastian.javacord.Javacord
import kaddy.plugin.java.JavaPluginLoader
import kaddy.util.LogOwner
import kaddy.util.Logging
import java.io.File
import java.util.Scanner

class KaddyBot private constructor (private val discordAPI: ImplDiscordAPI) : LogOwner, Kaddy by KaddyImpl(discordAPI) {

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
            pluginManager.registerInterface(JavaPluginLoader::class.java)
            val p = pluginManager.loadPlugin(File("./plugins/sample-plugin.jar"))
            pluginManager.enablePlugin(p ?: throw IllegalArgumentException())
            pluginManager.disablePlugin(p)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun disconnect() {
        discordAPI.disconnect()
    }
}
package sample.plugin

import kaddy.event.EventHandler
import kaddy.event.Listener
import kaddy.event.channel.ChannelChangeNameEvent
import kaddy.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        bot.pluginManager.registerEvents(this, this)
        logger.info { "Hi!" }
    }

    override fun onDisable() {
        logger.info { "Bye!" }
    }

    @EventHandler
    fun channelChangeName(event: ChannelChangeNameEvent) {
        event.channel.sendMessage("WOAH BUDDY")
    }
}
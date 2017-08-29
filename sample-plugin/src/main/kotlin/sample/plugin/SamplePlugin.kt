package sample.plugin

import com.github.plugkit.event.EventHandler
import com.github.plugkit.event.Listener
import kaddy.Kaddy
import kaddy.event.channel.ChannelChangeNameEvent
import kaddy.plugin.JavaPlugin

class SamplePlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        host.pluginManager.registerEvents(this, this)
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
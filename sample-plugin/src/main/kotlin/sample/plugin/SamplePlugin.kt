package sample.plugin

import kaddy.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {

    override fun onEnable() {
        logger.info { "Hi!" }
    }

    override fun onDisable() {
        logger.info { "Bye!" }
    }
}
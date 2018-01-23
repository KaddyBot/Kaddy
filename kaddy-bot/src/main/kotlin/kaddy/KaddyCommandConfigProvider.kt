package kaddy

import co.aikar.commands.CommandConfigProvider
import co.aikar.commands.JDACommandConfig
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

internal class KaddyCommandConfigProvider(private val config: Config) : CommandConfigProvider {
    override fun provide(event: MessageReceivedEvent): JDACommandConfig {
        val guildConfig = config.guilds[event.guild.idLong]
        return if (guildConfig != null) {
            guildConfig.jdaCommandConfig
        } else {
            config.jdaCommandConfig
        }
    }
}
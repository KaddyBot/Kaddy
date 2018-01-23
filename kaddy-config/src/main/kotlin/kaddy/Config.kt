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

import co.aikar.commands.JDACommandConfig
import dtmlibs.config.annotation.Comment
import dtmlibs.config.datasource.DataHandlingException
import dtmlibs.config.datasource.DataSource
import dtmlibs.config.datasource.hocon.HoconDataSource
import dtmlibs.config.properties.PropertiesWrapper
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class Config(@Transient private val configDir: Path = Paths.get(System.getProperty("user.home"), ".kaddy"),
             @Transient private val configFile: Path = configDir.resolve(Paths.get("kaddy.conf")),
             @Transient private val dataSource: DataSource = HoconDataSource.builder().setPath(configFile).build())
    : PropertiesWrapper() {

    fun isFileCreated(): Boolean = Files.exists(configFile)

    @Throws(IOException::class)
    fun createConfigFile() {
        configDir.toFile().mkdirs()
        Files.createFile(configFile)
    }

    @Throws(DataHandlingException::class)
    fun save() {
        dataSource.save(this)
    }

    @Throws(DataHandlingException::class)
    fun load() {
        dataSource.loadToObject(this)
    }

    @Comment("The command to run the jvm that will run the bot.")
    var javaCmd: String = "java"

    @Comment("Any args you wish to use with the bot's jvm.")
    var jvmArgs: MutableList<String> = mutableListOf()

    @Comment("Discord bot API token.")
    var botToken: String = ""

    @Comment("The command prefix that will be used if a more specific one has not been set for the context.")
    var defaultCommandPrefix: String = ">"

    @Transient
    val jdaCommandConfig: JDACommandConfig = BaseCommandConfig(this)

    val guilds: MutableMap<Long, GuildConfig> = mutableMapOf()

    class GuildConfig {
        @Comment("The command prefix for commands in this guild")
        var commandPrefix: String = ">"

        @Transient
        val jdaCommandConfig: JDACommandConfig = GuildCommandConfig(this)

        private class GuildCommandConfig(private val guildConfig: Config.GuildConfig) : JDACommandConfig() {
            override fun getStartsWith(): String = guildConfig.commandPrefix
        }
    }

    private class BaseCommandConfig(private val config: Config) : JDACommandConfig() {
        override fun getStartsWith(): String = config.defaultCommandPrefix
    }
}

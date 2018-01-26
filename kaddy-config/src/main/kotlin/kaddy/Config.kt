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

import co.aikar.commands.CommandConfig
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

class Config(@Transient val configDir: Path = Paths.get(System.getProperty("user.home"), ".kaddy"),
             @Transient val configFile: Path = configDir.resolve(Paths.get("kaddy.conf")),
             @Transient private val dataSource: DataSource = HoconDataSource.builder().setPath(configFile).build())
    : PropertiesWrapper(), CommandConfig {

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
        set(value) {
            field = value
            acfPrefixList = listOf(value)
        }

    @Transient
    private lateinit var acfPrefixList: List<String>

    override fun getCommandPrefixes(): List<String> {
        if (!::acfPrefixList.isInitialized) {
            acfPrefixList = listOf(defaultCommandPrefix)
        }
        return acfPrefixList
    }
}

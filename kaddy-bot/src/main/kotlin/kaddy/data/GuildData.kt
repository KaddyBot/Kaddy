/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy.data

import co.aikar.commands.CommandConfig
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import kaddy.KaddyBot
import net.dv8tion.jda.core.entities.Guild
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.concurrent.TimeUnit

class GuildData private constructor(val guildId: Long) : CommandConfig {

    private lateinit var acfPrefixList: List<String>

    private var _commandPrefix: String? = null

    var commandPrefix: String?
        get() = _commandPrefix
        @Throws(IllegalArgumentException::class)
        set(value) {
            _commandPrefix = value
            setCommandPrefix(guildId, value)
            if (value != null) {
                acfPrefixList = listOf(value)
            } else {
                acfPrefixList = listOf()
            }
        }

    override fun getCommandPrefixes(): List<String> {
        if (!::acfPrefixList.isInitialized) {
            acfPrefixList = if (commandPrefix != null) listOf(commandPrefix as String) else listOf()
        }
        if (acfPrefixList.isEmpty()) {
            return KaddyBot.config.commandPrefixes
        }
        return acfPrefixList
    }

    companion object {
        internal const val MAX_PREFIX_LENGTH: Int = 8

        private val guildDataCache: Cache<Long, GuildData> = CacheBuilder.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES).build()

        @JvmStatic
        fun forGuild(guild: Guild): GuildData {
            return forGuild(guild.idLong)
        }

        fun forGuild(guildId: Long): GuildData {
            return guildDataCache.get(guildId, {
                val guildData = GuildData(guildId)
                transaction {
                    val result = Guilds.select { Guilds.id eq guildId }.singleOrNull()
                    if (result != null) {
                        guildData._commandPrefix = result[Guilds.commandPrefix]
                    } else {
                        Guilds.insert {
                            it[id] = guildId
                        }
                    }
                }
                guildData
            })
        }

        private fun setCommandPrefix(guildId: Long, commandPrefix: String?) {
            val prefix = if (commandPrefix != null && commandPrefix.length > MAX_PREFIX_LENGTH) {
                throw IllegalArgumentException("The prefix exceeds the max length of $MAX_PREFIX_LENGTH")
            } else {
                commandPrefix
            }

            transaction {
                Guilds.update({ Guilds.id eq guildId }) {
                    it[Guilds.commandPrefix] = prefix
                }
            }
        }
    }
}
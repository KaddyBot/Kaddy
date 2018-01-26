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
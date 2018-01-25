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

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.concurrent.TimeUnit

lateinit var config: Config

fun connectDatabase(c: Config) {
    config = c
    Database.Companion.connect("jdbc:h2:file:${config.configDir}/database", "org.h2.Driver")
}

fun createTables() {
    transaction {
        create(Users, Guilds)
    }
}

object Users : Table() {
    val id = long("id").primaryKey()
}

object Guilds : Table() {
    val id = long("id").primaryKey()
    val commandPrefix = varchar("commandPrefix", 8)
            .default(config.defaultCommandPrefix)

    val prefixCache: Cache<Long, String> = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES).build()

    fun setCommandPrefix(guildId: Long, commandPrefix: String) {
        val prefix = if (commandPrefix.length > 8) {
            commandPrefix.substring(0, 8)
        } else {
            commandPrefix
        }
        prefixCache.put(guildId, prefix)

        transaction {
            if (Guilds.select{ Guilds.id eq guildId }.empty()) {
                Guilds.insert {
                    it[id] = guildId
                    it[Guilds.commandPrefix] = prefix
                }
                null // makes Exposed happy.
            } else {
                Guilds.update({ Guilds.id eq guildId }) {
                    it[Guilds.commandPrefix] = prefix
                }
                null // makes Exposed happy.
            }
        }
    }

    fun getCommandPrefix(guildId: Long): String? {
        var result: String? = prefixCache.getIfPresent(guildId)

        if (result != null) {
            return result
        }

        transaction {
            Guilds.select{ Guilds.id eq guildId }.forEach { result = it[Guilds.commandPrefix] }
        }
        if (result != null) {
            prefixCache.put(guildId, result)
        }
        return result
    }
}
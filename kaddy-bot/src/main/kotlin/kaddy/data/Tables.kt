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

import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

internal object Tables {
    internal fun createIfNonExistent() {
        transaction {
            create(Users, Guilds)
        }
    }
}

internal object Users : Table() {
    val id = long("id").primaryKey()
}

internal object Guilds : Table() {
    val id = long("id").primaryKey()
    val commandPrefix = varchar("commandPrefix", GuildData.MAX_PREFIX_LENGTH).nullable()
}
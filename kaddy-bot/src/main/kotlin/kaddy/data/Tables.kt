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
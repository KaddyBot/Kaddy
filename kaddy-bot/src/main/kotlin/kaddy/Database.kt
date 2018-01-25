package kaddy

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

fun connectDatabase(config: Config) {
    Database.Companion.connect("jdbc:h2:file:${config.configDir}/database.h2db", "org.h2.Driver")
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
}
package mc.tsukimiya.namelogger.infrastructure.dao

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object Accounts : UUIDTable("accounts") {
    val name: Column<String> = varchar("name", 16).uniqueIndex()
}

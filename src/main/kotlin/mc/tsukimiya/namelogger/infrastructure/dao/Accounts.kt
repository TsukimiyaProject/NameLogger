package mc.tsukimiya.namelogger.infrastructure.dao

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Accounts : UUIDTable("accounts") {
    val name = varchar("name", 16).uniqueIndex()
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}

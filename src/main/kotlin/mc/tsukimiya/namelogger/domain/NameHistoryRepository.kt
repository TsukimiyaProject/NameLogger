package mc.tsukimiya.namelogger.domain

import java.time.LocalDateTime
import java.util.*

internal interface NameHistoryRepository {
    fun find(uuid: UUID): UpdateNameHistory

    fun store(account: Account, time: LocalDateTime)
}

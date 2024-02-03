package mc.tsukimiya.namelogger.usecase

import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.domain.Name
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class FindNameUseCase(private val repository: AccountRepository) {
    fun execute(uuid: UUID): Name? {
        return transaction {
            repository.find(uuid)?.name
        }
    }
}
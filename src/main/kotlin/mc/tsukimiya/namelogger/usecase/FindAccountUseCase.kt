package mc.tsukimiya.namelogger.usecase

import mc.tsukimiya.namelogger.domain.Account
import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.domain.Name
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

internal class FindAccountUseCase(private val repository: AccountRepository) {
    fun execute(uuid: UUID): Account? {
        return transaction {
            repository.find(uuid)
        }
    }

    fun executeByName(name: String): Account? {
        return transaction {
            repository.findByName(Name(name))
        }
    }
}

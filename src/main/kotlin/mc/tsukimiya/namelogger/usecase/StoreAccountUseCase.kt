package mc.tsukimiya.namelogger.usecase

import mc.tsukimiya.namelogger.domain.Account
import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.domain.Name
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * アカウントを保存
 */
internal class StoreAccountUseCase(private val accountRepository: AccountRepository) {
    fun execute(uuid: UUID, name: String) {
        transaction {
            accountRepository.store(Account(uuid, Name(name)))
        }
    }
}
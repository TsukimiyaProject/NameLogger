package mc.tsukimiya.namelogger.usecase

import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.domain.Name
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * 名前からIDを取得
 */
internal class FindIDUseCase(private val accountRepository: AccountRepository) {
    fun execute(name: String): UUID? {
        return transaction {
            accountRepository.findByName(Name(name))?.id
        }
    }
}
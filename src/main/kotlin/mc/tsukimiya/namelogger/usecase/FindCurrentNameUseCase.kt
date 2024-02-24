package mc.tsukimiya.namelogger.usecase

import mc.tsukimiya.namelogger.domain.AccountRepository
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * 最新の名前を取得
 */
internal class FindCurrentNameUseCase(private val repository: AccountRepository) {
    fun execute(uuid: UUID): String? {
        return transaction {
            repository.find(uuid)?.name?.value
        }
    }
}
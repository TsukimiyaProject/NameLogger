package mc.tsukimiya.namelogger.presentation.api

import mc.tsukimiya.namelogger.NameLoggerAPI
import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.usecase.FindAccountUseCase
import mc.tsukimiya.namelogger.usecase.StoreAccountUseCase
import java.time.LocalDateTime
import java.util.*

internal class NameLoggerAPIImpl(private val accountRepository: AccountRepository) : NameLoggerAPI {
    override fun getCurrentName(uuid: UUID): String? {
        return FindAccountUseCase(accountRepository).execute(uuid)?.name?.value
    }

    override fun getIDByName(name: String): UUID? {
        return FindAccountUseCase(accountRepository).executeByName(name)?.id
    }

    override fun getOldNames(uuid: UUID): Map<LocalDateTime, String>? {
        TODO("Not yet implemented")
    }

    override fun updateAccountName(uuid: UUID, name: String) {
        StoreAccountUseCase(accountRepository).execute(uuid, name)
    }
}

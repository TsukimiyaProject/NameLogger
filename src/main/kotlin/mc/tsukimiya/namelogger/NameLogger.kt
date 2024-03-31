package mc.tsukimiya.namelogger

import mc.tsukimiya.namelogger.infrastructure.repository.AccountRepositoryImpl
import mc.tsukimiya.namelogger.usecase.FindCurrentNameUseCase
import mc.tsukimiya.namelogger.usecase.FindIDUseCase
import mc.tsukimiya.namelogger.usecase.StoreAccountUseCase
import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDateTime
import java.util.*

class NameLogger : JavaPlugin(), NameLoggerAPI {
    companion object {
        lateinit var api: NameLoggerAPI
    }

    private val accountRepository = AccountRepositoryImpl()

    override fun onEnable() {
        api = this
    }

    override fun getCurrentName(uuid: UUID): String? {
        return FindCurrentNameUseCase(accountRepository).execute(uuid)
    }

    override fun getIDByName(name: String): UUID? {
        return FindIDUseCase(accountRepository).execute(name)
    }

    override fun getOldNames(uuid: UUID): Map<LocalDateTime, String>? {
        TODO("Not yet implemented")
    }

    override fun updateAccountName(uuid: UUID, name: String) {
        StoreAccountUseCase(accountRepository).execute(uuid, name)
    }
}

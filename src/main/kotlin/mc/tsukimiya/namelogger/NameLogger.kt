package mc.tsukimiya.namelogger

import mc.tsukimiya.namelogger.infrastructure.repository.AccountRepositoryImpl
import mc.tsukimiya.namelogger.presentation.NameLoggerAPI
import mc.tsukimiya.namelogger.usecase.FindCurrentNameUseCase
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

    override fun getOldNames(uuid: UUID): Map<LocalDateTime, String>? {
        TODO("Not yet implemented")
    }
}

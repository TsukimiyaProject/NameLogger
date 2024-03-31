package mc.tsukimiya.namelogger

import mc.tsukimiya.namelogger.config.DatabaseConnector
import mc.tsukimiya.namelogger.infrastructure.repository.AccountRepositoryImpl
import mc.tsukimiya.namelogger.presentation.listener.PlayerJoinListener
import mc.tsukimiya.namelogger.usecase.FindAccountUseCase
import mc.tsukimiya.namelogger.usecase.StoreAccountUseCase
import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDateTime
import java.util.*

class NameLogger : JavaPlugin(), NameLoggerAPI {
    companion object {
        lateinit var instance: NameLogger
    }

    private val accountRepository = AccountRepositoryImpl()

    override fun onEnable() {
        instance = this

        dataFolder.mkdir()
        saveDefaultConfig()
        // resources/config.ymlに項目が追加された場合コンフィグに書き込む
        config.options().copyDefaults(true)
        saveConfig()

        server.pluginManager.registerEvents(PlayerJoinListener(), this)

        DatabaseConnector().connect(config)
    }

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

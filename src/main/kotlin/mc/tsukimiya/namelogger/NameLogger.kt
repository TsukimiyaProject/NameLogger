package mc.tsukimiya.namelogger

import mc.tsukimiya.namelogger.config.DatabaseConnector
import mc.tsukimiya.namelogger.domain.AccountRepository
import mc.tsukimiya.namelogger.infrastructure.repository.AccountRepositoryImpl
import mc.tsukimiya.namelogger.presentation.api.NameLoggerAPIImpl
import mc.tsukimiya.namelogger.presentation.listener.PlayerJoinListener
import org.bukkit.plugin.java.JavaPlugin

class NameLogger : JavaPlugin() {
    companion object {
        // トランザクションとか気にするとき用
        val accountRepository: AccountRepository = AccountRepositoryImpl()

        // トランザクション気にしないならAPI使ったほうがいい
        val api: NameLoggerAPI = NameLoggerAPIImpl(accountRepository)
    }

    override fun onEnable() {
        dataFolder.mkdir()
        saveDefaultConfig()
        // resources/config.ymlに項目が追加された場合コンフィグに書き込む
        config.options().copyDefaults(true)
        saveConfig()

        server.pluginManager.registerEvents(PlayerJoinListener(), this)

        DatabaseConnector().connect(config)
    }
}

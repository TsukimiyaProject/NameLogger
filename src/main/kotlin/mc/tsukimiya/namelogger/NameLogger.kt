package mc.tsukimiya.namelogger

import mc.tsukimiya.namelogger.config.DatabaseConnector
import mc.tsukimiya.namelogger.presentation.api.NameLoggerAPIImpl
import mc.tsukimiya.namelogger.presentation.listener.PlayerJoinListener
import org.bukkit.plugin.java.JavaPlugin

class NameLogger : JavaPlugin() {
    companion object {
        val api: NameLoggerAPI = NameLoggerAPIImpl()
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

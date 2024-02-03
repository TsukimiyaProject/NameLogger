package mc.tsukimiya

import org.bukkit.plugin.java.JavaPlugin
import java.time.LocalDateTime
import java.util.*

class NameLogger : JavaPlugin(), NameLoggerAPI {
    companion object {
        lateinit var api: NameLoggerAPI
    }

    override fun onEnable() {
        api = this
    }

    override fun getCurrentName(uuid: UUID): String {
        TODO("Not yet implemented")
    }

    override fun getOldNames(uuid: UUID): Map<LocalDateTime, String> {
        TODO("Not yet implemented")
    }
}
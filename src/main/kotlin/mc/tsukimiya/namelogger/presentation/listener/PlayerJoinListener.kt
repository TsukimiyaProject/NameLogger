package mc.tsukimiya.namelogger.presentation.listener

import mc.tsukimiya.namelogger.NameLogger
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        NameLogger.instance.updateAccountName(player.uniqueId, player.name)
    }
}

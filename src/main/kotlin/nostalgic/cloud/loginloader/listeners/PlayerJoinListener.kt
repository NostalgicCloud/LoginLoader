package nostalgic.cloud.loginloader.listeners

import nostalgic.cloud.loginloader.utils.ConfigUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val configUtils: ConfigUtils) : Listener{
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        println("${player.name} has joined the server.")
        configUtils.spawnPlayer(player)
        // Add your logic here
    }
}
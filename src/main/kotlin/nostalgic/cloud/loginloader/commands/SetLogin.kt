package nostalgic.cloud.loginloader.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import nostalgic.cloud.loginloader.utils.ConfigUtils

class SetLogin (private val configUtils: ConfigUtils): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender
            val location = player.location
            val locationString = "${location.world?.name},${location.x},${location.y},${location.z},${location.yaw},${location.pitch}"
            val config = configUtils.getConfig()
            config.set("loginLocation", locationString)
            configUtils.saveConfig()
            player.sendMessage("Login location set successfully.")
            return true
        }
        sender.sendMessage("This command can only be run by a player.")
        return false
    }
}
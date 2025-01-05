package nostalgic.cloud.loginloader.utils
import org.bukkit.Bukkit
import org.bukkit.Location
import java.io.File
import java.io.IOException
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player

class ConfigUtils(private val dataFolder: File) {

    private lateinit var configFile: File
    private lateinit var config: FileConfiguration

    fun initialize(dataFolder: File) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }

        configFile = File(dataFolder, "config.yml")
        if (!configFile.exists()) {
            try {
                configFile.createNewFile()
                config = YamlConfiguration.loadConfiguration(configFile)
                config.set("loginLocation", "world,0,64,0")
                config.save(configFile)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            config = YamlConfiguration.loadConfiguration(configFile)
        }
    }

    fun getConfig(): FileConfiguration {
        return config
    }

    fun saveConfig() {
        try {
            config.save(configFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun spawnPlayer(player: Player) {
        val locationString = config.getString("loginLocation") ?: return
        val parts = locationString.split(",")
        if (parts.size == 6) {
            val world = Bukkit.getWorld(parts[0])
            val x = parts[1].toDoubleOrNull() ?: return
            val y = parts[2].toDoubleOrNull() ?: return
            val z = parts[3].toDoubleOrNull() ?: return
            val yaw = parts[4].toFloatOrNull() ?: return
            val pitch = parts[5].toFloatOrNull() ?: return
            if (world != null) {
                val location = Location(world, x, y, z, yaw, pitch)
                player.teleport(location)
            }
        }
    }
}
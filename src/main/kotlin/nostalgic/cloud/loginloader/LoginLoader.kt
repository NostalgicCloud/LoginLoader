
package nostalgic.cloud.loginloader

import nostalgic.cloud.loginloader.commands.*
import nostalgic.cloud.loginloader.listeners.PlayerJoinListener
import nostalgic.cloud.loginloader.utils.ConfigUtils
import org.bukkit.Bukkit

import org.bukkit.plugin.java.JavaPlugin

public class LoginLoader : JavaPlugin() {

    private lateinit var configUtils: ConfigUtils

    override fun onEnable() {
        // Plugin startup logic
        logger.info("*******************Vault Is Loading**********************");
        configUtils = ConfigUtils(dataFolder)
        configUtils.initialize(dataFolder)
        registerCommands();
        registerListener();
    }

    private fun registerCommands(){
        //this.getCommand("v")?.setExecutor(CustomGuiCommand(this ))
        this.getCommand("setLocation")?.setExecutor(SetLogin(configUtils ))
        logger.info("Registering Commands For vault Done")
    }

    private fun registerListener(){
        logger.info("Registering Listeners for Vault")
        Bukkit.getServer().pluginManager.registerEvents(PlayerJoinListener(configUtils), this)
        logger.info("Registering Listeners For vault Done")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("***************Vault is Now Unloading****************");
        configUtils.saveConfig()
    }
}



package nostalgic.cloud.loginloader.itembuilder

import com.destroystokyo.paper.profile.PlayerProfile
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.net.URL
import java.util.*

class ItemBuilder {

    companion object{
        fun create(material: Material, name:String, color: TextColor ) : ItemStack{
            val item = ItemStack(material)
            val itemMeta = item.itemMeta
            itemMeta.displayName(Component.text(name).color(color))


            item.setItemMeta(itemMeta)
            return item
        }

        val guiMap: MutableMap<UUID, Inventory> = mutableMapOf()
    }

    fun createHead(url: URL?): ItemStack {
        val uuid = UUID.randomUUID()
        val profile: PlayerProfile = Bukkit.createProfile(uuid)
        profile.setTextures(profile.textures)
        val next = profile.textures
        profile.setTextures(next)
        val head = ItemStack(Material.PLAYER_HEAD)
        val skullMeta = head.itemMeta as SkullMeta
        skullMeta.playerProfile = profile
        return head
    }
}
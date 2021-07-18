package uhfinn.voidinstantkill.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventException;
import org.bukkit.plugin.Plugin;
import uhfinn.voidinstantkill.Main;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import uhfinn.voidinstantkill.Modules.CustomYMLFiles;

import java.util.Objects;

public class VoidDrop implements Listener {

    Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onVoid(PlayerMoveEvent event){
        FileConfiguration config = CustomYMLFiles.getCustomConfigFile("Config.yml"); // This is slightly inefficient by calling it every single move event, will be looking into a way of improving this :)
        Player p = event.getPlayer();
        if(p.getLocation().getY() < config.getInt("Custom Respawn Y Level")){
            if(!config.getBoolean("Instant Respawn")){
                p.damage(2000);
            }
            if(config.getBoolean("Instant Respawn")){
                Location respawn =plugin.getServer().getWorld(p.getWorld().getName()).getSpawnLocation();
                Location checkLoc = respawn;
                checkLoc.setY(checkLoc.getY() + 1);
                if(checkLoc.getBlock().getType() != Material.AIR && checkLoc.getBlock().getType() != Material.WATER){
                    respawn = respawn.getWorld().getHighestBlockAt(respawn).getLocation();
                    respawn.setY(respawn.getY() + 1);
                }
                p.teleport(respawn);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Instant Respawn Message")));

                double health = 0;
                try {
                    health = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                } catch(NoClassDefFoundError e) {
                    health = 20;
                }
                p.setHealth(health);
                p.setFoodLevel(20);
                if(config.getBoolean("Instant Respawn Clear Inventory")){
                    p.getInventory().clear();
                    p.setTotalExperience(0);
                }
            }
        }
    }
}

package uhfinn.voidinstantkill.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
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
                Location respawn = Objects.requireNonNull(plugin.getServer().getWorld(p.getWorld().getName())).getSpawnLocation();
                p.teleport(respawn);

                double health = Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                p.setHealth(health);
                p.setFoodLevel(20);
                if(config.getBoolean("Instant Respawn Clear Inventory")){
                    p.getInventory().clear();
                    p.setTotalExperience(0);
                }
                if(config.getString("Instant Respawn Message") != null && !config.getString("Instant Respawn Message").equals("") && !config.getString("Instant Respawn Message").equals(" ")) p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Instant Respawn Message")));
            }
        }
    }
}

package Listener;

import VoidInstantKill.me;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class VoidDrop implements Listener {
    private final me plugin;

    public VoidDrop(me plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onVoid(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(p.getLocation().getY() < plugin.getConfig().getInt("Custom Respawn Y Level")){
            if(!plugin.getConfig().getBoolean("InstantRespawn")){
                p.damage(2000);
                System.out.println("Instant Respawn: False");
            }
            if(plugin.getConfig().getBoolean("InstantRespawn")){
                System.out.println("Instant Respawn: True");
                Location respawn = Objects.requireNonNull(plugin.getServer().getWorld(p.getWorld().getName())).getSpawnLocation();
                p.teleport(respawn);

                double health = Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                p.setHealth(health);
                p.setFoodLevel(20);
                if(plugin.getConfig().getBoolean("InstantRespawn-ClearInventory")){
                    p.getInventory().clear();
                }
                p.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("InstantRespawn-Message")));
            }
        }
    }
}

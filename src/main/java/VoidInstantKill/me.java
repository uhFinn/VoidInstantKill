package VoidInstantKill;

import Listener.VoidDrop;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;


public final class me extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("[VoidInstantKill] Loading Plugin...");
        new VoidDrop(this);

        FileConfiguration config = getConfig();
        config.addDefault("Custom Respawn Y Level", 0);
        config.addDefault("InstantRespawn", false);
        config.addDefault("InstantRespawn-Message", "You Died From Falling In The Void");
        config.addDefault("InstantRespawn-ClearInventory", true);
        config.options().copyDefaults(true);
        saveConfig();

        System.out.println("[VoidInstantKill] Generated Config Files");


        System.out.println("[VoidInstantKill] Loaded Plugin!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("VIK-reload")){
            if(sender.hasPermission("vik.reload") || sender.isOp()) {
                reloadConfig();
                saveConfig();
                sender.sendMessage("[VoidInstantKill] Reloaded config.yml!");
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        System.out.println("[VoidInstantKill] Shutting Down...");
    }
}

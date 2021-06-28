package uhfinn.voidinstantkill.Modules;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uhfinn.voidinstantkill.Main;

import java.io.File;

public class CustomYMLFiles {

    private static FileConfiguration Config = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Config.yml"));

    public static FileConfiguration getCustomConfigFile(String FileName) {
        if(FileName.equals("Config.yml")) return Config;
        return null;
    }

    public static String Reload() {
        String output;

        File Configur = new File(Main.INSTANCE().getDataFolder() + "/Config.yml");
        if(!Configur.exists()) {
            Main.getPlugin(Main.class).saveResource("Config.yml", false);
            System.out.println("[VoidInstantKill] Generated Config.yml File");
            output = "&c[VoidInstantKill] &fSuccessfully generated a Config.yml file";
        } else output = "&c[VoidInstantKill] &fSuccessfully reloaded the Config.yml file";

        Config = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Config.yml"));

        return ChatColor.translateAlternateColorCodes('&', output);
    }
}

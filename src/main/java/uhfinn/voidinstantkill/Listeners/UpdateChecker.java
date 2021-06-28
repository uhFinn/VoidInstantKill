package uhfinn.voidinstantkill.Listeners;

import com.google.common.io.Closer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import uhfinn.voidinstantkill.Main;
import uhfinn.voidinstantkill.Modules.CustomYMLFiles;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker implements Listener
{

    FileConfiguration config = CustomYMLFiles.getCustomConfigFile("Config.yml");
    private String spigotVersion = "0.0";

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        if(p.isOp()) {
            if(config.getBoolean("Update Notifications")) {
                int ID = 88464;
                try {
                    final HttpsURLConnection con = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
                    con.setRequestMethod("GET");
                    spigotVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
                } catch (final IOException error) {
                    error.printStackTrace();
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
                    public void run() {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[VoidInstantKill] &fVersion " + spigotVersion + " has been released!\nGet it now at: &nhttps://www.spigotmc.org/resources/" + ID));
                        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 5, 1);
                    }
                }, 400L);
            }
        }
    }
}

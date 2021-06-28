package uhfinn.voidinstantkill;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import uhfinn.voidinstantkill.Listeners.UpdateChecker;
import uhfinn.voidinstantkill.Listeners.VoidDrop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import uhfinn.voidinstantkill.Modules.CustomYMLFiles;


public final class Main extends JavaPlugin implements Listener {

    private static Main _instance;
    public static Main INSTANCE()
    {
        return _instance;
    }

    @Override
    public void onEnable() {

        System.out.println("[VoidInstantKill] Initiating Listeners...");
        _instance=this;
        addListener(new VoidDrop());
        addListener(new UpdateChecker());

        System.out.println("[VoidInstantKill] Loading configurations...");

        CustomYMLFiles.Reload(); //Old version went through line by line writing an un-formatted Config, This simplifies things

        System.out.println("[VoidInstantKill] Loaded Plugin!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("VIK-reload")){
            if(sender.hasPermission("vik.reload") || sender.isOp()) {
                String string = CustomYMLFiles.Reload();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
            }
        }
        return false;
    }

    public void addListener(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        System.out.println("[VoidInstantKill] Shutting Down...");
    }
}

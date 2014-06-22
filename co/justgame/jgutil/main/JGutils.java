package co.justgame.jgutil.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import co.justgame.jgutil.action.Action;
import co.justgame.jgutil.action.FreezeListener;
import co.justgame.jgutil.action.KickListener;
import co.justgame.jgutil.action.LookupLogoutListener;
import co.justgame.jgutil.action.TeleportAction;
import co.justgame.jgutil.action.godListener;
import co.justgame.jgutil.listener.Listeners;
import co.justgame.jgutil.resources.Messages;

public class JGutils extends JavaPlugin{
    
    private static Plugin instance;
    private static FileConfiguration config;
    
    private static int DESPAWN;
    private static int TIMEOUT;
    
    @Override
    public void onEnable(){
        instance = this;
        Messages.loadMessages();
        TeleportAction.startLoop();
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new godListener(), this);
        getServer().getPluginManager().registerEvents(new KickListener(), this);
        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getServer().getPluginManager().registerEvents(new LookupLogoutListener(), this);
        
        config = this.getConfig();

        config.options().header("");
        config.addDefault("default-despawn-radius", 20);
        config.addDefault("request-timeout", 60);
        config.options().copyDefaults(true);
        saveConfig();
        
        DESPAWN = config.getInt("default-despawn-radius");
        TIMEOUT = config.getInt("request-timeout");
        
        getLogger().info("JGutils has been enabled");
    }
    
    @Override
    public void onDisable(){
        getLogger().info("JGutils has been disabled");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        String command =  cmd.getName();
        Action.fireAction(sender, cmd, args, command); 
        return true;
    }
    
    public static int getTimeOut(){
        return TIMEOUT;
    }
    
    public static int getDespawnRadius(){
        return DESPAWN;
    }

    public static Plugin getInstance(){
        return instance;
    }

}

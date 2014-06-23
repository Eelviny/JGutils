package co.justgame.jgutil.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import co.justgame.jgutil.main.JGutils;

public class LookupLogoutListener implements Listener{
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void logoutEvent(PlayerQuitEvent e){
        PlayerFile.savePlayerFile(e.getPlayer(), getLastLogin(e.getPlayer()));
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void kickEvent(PlayerKickEvent e){
        PlayerFile.savePlayerFile(e.getPlayer(), getLastLogin(e.getPlayer()));
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void loginEvent(PlayerJoinEvent e){
       saveLastLogin(e.getPlayer());
    }
    
    public static String getLastLogin(Player p){
        try{
            File dir = new File(JGutils.getInstance().getDataFolder() + File.separator + "Last Login" + File.separator);
            Files.createDirectories(dir.toPath());
            File loginFile = new File(dir, "LastLogin.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(loginFile);
            return config.getString("lastlogin."+p.getUniqueId(), "§UNKNOWN");
        }catch (IOException e){
            e.printStackTrace(); return new Date().toString();
        }
    }
    
    public static void saveLastLogin(Player p){
        try{
            File dir = new File(JGutils.getInstance().getDataFolder() + File.separator + "Last Login" + File.separator);
            Files.createDirectories(dir.toPath());
            File loginFile = new File(dir, "LastLogin.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(loginFile);
            config.set("lastlogin."+p.getUniqueId(), new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss G z").format(new Date()));
            config.save(loginFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

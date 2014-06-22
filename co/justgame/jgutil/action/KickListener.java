package co.justgame.jgutil.action;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.justgame.jgutil.main.JGutils;
import co.justgame.jgutil.resources.Messages;


public class KickListener implements Listener {

    final static String KICK_MESSAGE = Messages.get("jgutils.kick.message");
    static ArrayList<UUID> kicked = new ArrayList<UUID>();
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void loginEvent(final PlayerJoinEvent e){
       if(kicked.contains(e.getPlayer().getUniqueId())){
           Bukkit.getScheduler().scheduleSyncDelayedTask(JGutils.getInstance(), new Runnable(){
            @Override
            public void run(){
                 e.getPlayer().kickPlayer(KICK_MESSAGE);
            }}, 3);
          e.setJoinMessage(null);
       }
    }
    
    public static void kick(Player p){
        kicked.add(p.getUniqueId());
        p.kickPlayer(KICK_MESSAGE);
    }
    
    public static boolean iskicked(Player p){
        return kicked.contains(p);
    }
    
    public static void unkick(Player p){
        kicked.remove(p.getUniqueId());
    }
}

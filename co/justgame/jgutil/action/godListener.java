package co.justgame.jgutil.action;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class godListener implements Listener{
    
    static ArrayList<Player> godmode = new ArrayList<Player>();
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void damageEvent(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
             if(godmode.contains(p))
                 e.setCancelled(true);
        }    
    }
    
    public static void entergodMode(Player p){
        godmode.add(p);
    }
    
    public static void exitgodMode(Player p){
        godmode.remove(p);
    }
    
    public static boolean isIngodMode(Player p){
        return godmode.contains(p);
    }
}


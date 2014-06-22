package co.justgame.jgutil.action;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class FreezeListener implements Listener{
    
static ArrayList<Player> frozen = new ArrayList<Player>();
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void moveEvent(PlayerMoveEvent e){
       if(frozen.contains(e.getPlayer())){    e.getFrom().setPitch(e.getTo().getPitch());
                                              e.getFrom().setYaw(e.getTo().getYaw());
           e.setTo(e.getFrom());
       }
    }
    
    public static void freeze(Player p){
        frozen.add(p);
        p.getLocation().getBlock().setType(Material.ICE);
        p.getLocation().add(0, 1, 0).getBlock().setType(Material.ICE);
        p.getWorld().playSound(p.getLocation(), Sound.CAT_HISS, 1f, 0);
    }
    
    public static void unfreezeAll(){
        for(Player p : frozen){
            melt(p);
        }
    }
    
    public static void melt(Player p){
        frozen.remove(p);
        p.getLocation().getBlock().setType(Material.AIR);
        p.getLocation().add(0, 1, 0).getBlock().setType(Material.AIR);
        for(int i = 0; i < 8; i++)
            p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, i);
        p.getWorld().playSound(p.getLocation(), Sound.FIZZ, 2, 0);
    }
    
    public static boolean isFrozen(Player p){
        return frozen.contains(p);
    }

}

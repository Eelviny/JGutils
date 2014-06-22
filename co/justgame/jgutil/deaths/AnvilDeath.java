package co.justgame.jgutil.deaths;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import co.justgame.jgutil.main.JGutils;


public class AnvilDeath extends Death{
    public AnvilDeath(Player p){
        super(p);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void run(){
        super.run();
        final Location loc = p.getLocation();
        final FallingBlock fb = loc.getWorld().spawnFallingBlock(loc.add(0, 30, 0), Material.ANVIL, (byte)0);
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(JGutils.getInstance(), new Runnable(){
            @Override
            public void run(){
                fb.remove();
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 2, 0);
                p.setHealth(0.0);
            }
        }, 40);
    }
}

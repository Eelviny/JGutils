package co.justgame.jgutil.deaths;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
        final FallingBlock fb = loc.getWorld().spawnFallingBlock(loc, Material.ANVIL, (byte)0);
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(JGutils.getInstance(), new Runnable(){
            @Override
            public void run(){
                fb.remove();
                p.setHealth(0.0);
            }
        }, 50);
    }
}

package co.justgame.jgutil.deaths;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

import co.justgame.jgutil.main.JGutils;

public class LightningDeath extends Death{
    
    public LightningDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        p.setPlayerWeather(WeatherType.DOWNFALL);
        Bukkit.getScheduler().scheduleSyncDelayedTask(JGutils.getInstance(), new Runnable(){
            @Override
            public void run(){
                p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
                p.setHealth(0.0);
            }
        }, 80);
        
    }
}

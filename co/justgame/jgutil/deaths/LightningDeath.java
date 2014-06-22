package co.justgame.jgutil.deaths;

import org.bukkit.entity.Player;

public class LightningDeath extends Death{
    
    public LightningDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        p.getLocation().getWorld().strikeLightning(p.getLocation());
    }
}

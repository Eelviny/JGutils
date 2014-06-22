package co.justgame.jgutil.deaths;

import org.bukkit.entity.Player;

public class ExplosionDeath extends Death{
    
    public ExplosionDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        p.getLocation().getWorld().createExplosion(p.getLocation(), 10);
    }
}

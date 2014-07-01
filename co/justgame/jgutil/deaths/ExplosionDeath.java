package co.justgame.jgutil.deaths;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ExplosionDeath extends Death{
    
    public ExplosionDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        Location l = p.getLocation();
        p.getLocation().getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 10, false, false);
    }
}

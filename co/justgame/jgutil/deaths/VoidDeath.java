package co.justgame.jgutil.deaths;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class VoidDeath extends Death{
    
    public VoidDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        Location l = p.getLocation();
        p.teleport(new Location(l.getWorld(), l.getX(), -2, l.getZ()));
    }
}

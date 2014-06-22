package co.justgame.jgutil.deaths;

import org.bukkit.entity.Player;

public class FireDeath extends Death{
    
    public FireDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        p.setFireTicks(500);
    }
}

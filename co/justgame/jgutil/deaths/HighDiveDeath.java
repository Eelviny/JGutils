package co.justgame.jgutil.deaths;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class HighDiveDeath extends Death{
    
    public HighDiveDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
        p.setVelocity(new Vector(0,5,0));
    }
}

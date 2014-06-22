package co.justgame.jgutil.deaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.entity.Player;


public class DeathList {
    
    public static void PlayRandomDeath(Player p){
        ArrayList<Death> deaths = new ArrayList<Death>(Arrays.asList( 
                new HighDiveDeath(p), new FireDeath(p), new VoidDeath(p), new AnvilDeath(p),
                new LightningDeath(p), new ExplosionDeath(p)));
        
        Random r = new Random();
        int i = r.nextInt(deaths.size());
        if(i < 0) i = 0;
        deaths.get(i).run();
    }
}

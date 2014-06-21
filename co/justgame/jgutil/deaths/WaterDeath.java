package co.justgame.jgutil.deaths;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import co.justgame.jgutil.main.JGutils;

public class WaterDeath extends Death{
    
    public WaterDeath(Player p){
        super(p);
    }
    
    @Override
    public void run(){
        super.run();
       
        int radius = 1;
        final Location loc = p.getLocation();
        final Block legs = loc.getBlock();
        final Block head = loc.add(0, 1, 0).getBlock();
        
        for (int x = -(radius); x <= radius; x ++){
          for (int y = -(radius); y <= radius; y ++){
            for (int z = -(radius); z <= radius; z ++){
                Block b = loc.getBlock().getRelative(x,y,z);
              if (b.getType() == Material.AIR && b != legs && b != head){
                  b.setType(Material.SPONGE);
               }
             }
           }
        }
        legs.setType(Material.WATER); head.setType(Material.WATER);
        
        p.setRemainingAir(3);
            
        Bukkit.getScheduler().scheduleSyncDelayedTask(JGutils.getInstance(), new Runnable(){
            @Override
            public void run(){
                int radius = 2;
                for (int x = -(radius); x <= radius; x ++){
                    for (int y = -(radius); y <= radius; y ++){
                      for (int z = -(radius); z <= radius; z ++){
                          Block b = loc.getBlock().getRelative(x,y,z);
                        if (b.getType() == Material.SPONGE){
                           b.setType(Material.AIR);
                         }
                       }
                     }
                  }
                legs.setType(Material.AIR); head.setType(Material.AIR);
            }
        }, 100);
    }
}

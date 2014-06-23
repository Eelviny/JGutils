package co.justgame.jgutil.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import co.justgame.jgutil.main.JGutils;

public class PlayerFile {
    public static void savePlayerFile(Player p, String d){
        File pf = createPlayerFile(p.getUniqueId().toString(), true);
        FileConfiguration config = YamlConfiguration.loadConfiguration(pf);
        
        String pn = p.getName();
        config.set("name", pn);
        config.set("uuid", p.getUniqueId().toString());
        
        Location loc = p.getLocation();
        config.set("loc.x", loc.getX());
        config.set("loc.y", loc.getY());
        config.set("loc.z", loc.getZ());
        config.set("loc.world", loc.getWorld().getName());
        
        config.set("login", d);
        config.set("mode", StringUtils.capitalize(p.getGameMode().name().toLowerCase()));
        config.set("life", p.getHealth());
        config.set("hunger", p.getFoodLevel());
        config.set("sat", (double) p.getSaturation());
        config.set("exp", p.getLevel());
        config.set("god", godListener.isIngodMode(p));
        config.set("fly", p.getAllowFlight());
        try{
            config.save(pf);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static String readPlayerFile(String p){
        try{
            File pf = findBestMatch(p);
            
            if(!pf.exists()) return null;
            else{
                FileConfiguration config = YamlConfiguration.loadConfiguration(pf);
                return LookupAction.toString(config.getString("name"), config.getString("uuid"),
                                             config.getDouble("loc.x"), config.getDouble("loc.y"),
                                             config.getDouble("loc.z"), config.getString("loc.world"), config.getString("login"),
                                             config.getString("mode"), config.getDouble("life"), 
                                             config.getInt("hunger"), (float) config.getDouble("sat"), 
                                             config.getInt("exp"), config.getBoolean("god"), config.getBoolean("fly"));
            }
        }catch(NullPointerException npe){
            System.out.println("Attempted to read empty player file!");
            return null;
        }
    }
    
    private static File findBestMatch(String s){
        File dir = new File(JGutils.getInstance().getDataFolder() + File.separator + "Players" + File.separator);
        TreeMap<Integer, File> tm = new TreeMap<Integer, File>();
        for(File f : dir.listFiles()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(f);
            tm.put(StringUtils.getLevenshteinDistance(config.getString("name"), s), f);
        }
        if(tm.firstEntry().getKey() < 16)
            return tm.firstEntry().getValue();
        else return null;
    }
    
    private static File createPlayerFile(String UUID, boolean bool){
        try{

            File dir = new File(JGutils.getInstance().getDataFolder() + File.separator + "Players" + File.separator);
            Files.createDirectories(dir.toPath());
            File playerFile = new File(dir, UUID + ".yml");

            if(!playerFile.exists() && bool){
                playerFile.createNewFile();
            }

            return playerFile;

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

package co.justgame.jgutil.resources;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import co.justgame.jgutil.main.JGutils;

public class Messages {

    private static HashMap<String, String> messageData = new HashMap<String, String>();
    static Plugin plugin = JGutils.getInstance();

    public static synchronized String get(String key){
        return messageData.get(key);
    }

    public static synchronized void loadMessages(){

        File Messages = new File(plugin.getDataFolder() + File.separator + "messages.yml");
        if(!Messages.exists()) try{
            Messages.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        setMessage("jgutils.noperm", "&cYou do not have permission to use this command!");
        setMessage("jgutils.notplayer", "&cYou must be a player to use this command!");
        setMessage("jgutils.noplayer", "&cThe player specified does not exist!");
        setMessage("jgutils.existnoplayer", "&cThe player %p% does not exist!");
        setMessage("jgutils.moreplayer", "&cMore than one player found. Please be more specific!");
        
        setMessage("jgutils.welcome.message", "&8Your Welcome Message Here!/n  &c- I am a new line");
        setMessage("jgutils.welcome.usage", "&cUsage: /welcome");
        
        setMessage("jgutils.account.message", "&8Your Account Message Here!");
        setMessage("jgutils.account.usage", "&cUsage: /account");
        
        setMessage("jgutils.chat.message", "&8Your Chat Message Here!");
        setMessage("jgutils.chat.usage", "&cUsage: /chat");
        
        setMessage("jgutils.rules.message", "&8Your Rules Here!");
        setMessage("jgutils.rules.usage", "&cUsage: /rules");
        
        setMessage("jgutils.help.message", "&8Your Help Message Here!");
        setMessage("jgutils.help.usage", "&cUsage: /help");
        
        setMessage("jgutils.nav.message", "&8Your Navigation Message Here!");
        setMessage("jgutils.nav.usage", "&cUsage: /navigation");
        
        setMessage("jgutils.claim.message", "&8Your Claim Message Here!");
        setMessage("jgutils.claim.usage", "&cUsage: /claim");
        
        setMessage("jgutils.web.message", "&8Your Website Message Here!");
        setMessage("jgutils.web.usage", "&cUsage: /website");
        
        setMessage("jgutils.despawn.message", "&eRemoved %n% Entities!");
        setMessage("jgutils.despawn.null", "&cEntity type %e% does not exist!");
        setMessage("jgutils.despawn.usage", "&cUsage: /despawn <Entity> <Radius>");
        
        setMessage("jgutils.fly.enable", "&aEnabled Fly Mode!");
        setMessage("jgutils.fly.otherenable", "&aEnabled %p%'s Fly Mode!");
        setMessage("jgutils.fly.disable", "&cDisabled Fly Mode!");
        setMessage("jgutils.fly.otherdisable", "&cDisabled %p%'s Fly Mode!");
        setMessage("jgutils.fly.set", "&eSet Fly Speed to %i%!");
        setMessage("jgutils.fly.otherset", "&eSet %p%'s Speed to %i%!");
        setMessage("jgutils.fly.usage", "&cUsage: /fly || /fly <Speed> || /fly <Player> <Speed>");
        
        setMessage("jgutils.god.enable", "&aEnabled god Mode!");
        setMessage("jgutils.god.otherenable", "&aEnabled %p%'s god Mode!");
        setMessage("jgutils.god.disable", "&cDisabled god Mode!");
        setMessage("jgutils.god.otherdisable", "&cDisabled %p%'s god Mode!");
        setMessage("jgutils.god.usage", "&cUsage: /god || /god <Player>");
        
        setMessage("jgutils.kick.info", "&cKicked %p%!");
        setMessage("jgutils.kick.message", "&8Your Kick Message Here!");
        setMessage("jgutils.kick.usage", "&cUsage: /kick <Player>");
        
        setMessage("jgutils.suicide.usage", "&cUsage: /suicide");
        
        setMessage("jgutils.kill.message", "&cYou killed %p%!");
        setMessage("jgutils.kill.usage", "&cUsage: /kill <Player>");
        
        setMessage("jgutils.lookup.usage", "&cUsage: /lookup");
        
        setMessage("jgutils.tp.usage.tp", "&cUsage: /tp <Player> <Player>");
        setMessage("jgutils.tp.usage.tpa", "&cUsage: /tpaccept");
        setMessage("jgutils.tp.usage.tpd", "&cUsage: /tpdeny");
        setMessage("jgutils.tp.usage.tpl", "&cUsage: /tplist");
        setMessage("jgutils.tp.usage.tpp", "&cUsage: /tppos <X> <Y> <Z> <World>");
        
        setMessage("jgutils.tp.send", "&aSent teleport request to %p%!");
        setMessage("jgutils.tp.fromothersend", "&a%p% requests to teleport to you!");
        setMessage("jgutils.tp.toothersend", "&a%p% requests that you teleport to them!");
        
        setMessage("jgutils.tp.me", "&cAt least one player must be yourself!");
        setMessage("jgutils.tp.none", "&cYou have no pending requests!");
                
        setMessage("jgutils.tp.accept", "&e%p% accepted teleport request!");
        setMessage("jgutils.tp.tootheraccept", "&eAccepted teleport to %p%!");
        setMessage("jgutils.tp.fromotheraccept", "&eAccepted teleport from %p%!");
        
        setMessage("jgutils.tp.deny", "&c%p% denied teleport request!");
        setMessage("jgutils.tp.otherdeny", "&cDenied teleport from %p%!");


        try{
            FileConfiguration config = YamlConfiguration.loadConfiguration(Messages);
            for(String message: config.getConfigurationSection("").getKeys(true)){
                messageData.put(message, formatString(config.getString(message)));
            }
        }catch (Exception e){
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cError loading messages.yml!");
        }

    }

    private static void setMessage(String name, String message){
        File f = new File(plugin.getDataFolder() + File.separator + "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(f);
        if(!config.isSet(name)){
            config.set(name, message);
            try{
                config.save(f);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public static void sendMultilineMessage(CommandSender player, String message){
        if(player != null && message != null){
            String[] s = message.split("/n");
            for(String m: s){
                player.sendMessage(m);
            }
        }
    }

    private synchronized static String formatString(String string){
        return string.replaceAll("&", "§");
    }
}

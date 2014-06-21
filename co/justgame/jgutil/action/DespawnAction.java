package co.justgame.jgutil.action;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import co.justgame.jgutil.main.JGutils;
import co.justgame.jgutil.resources.Messages;

public class DespawnAction {
    final static String DESPAWN_MESSAGE = Messages.get("jgutils.despawn.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.despawn.usage");
    final static String NULL_MESSAGE = Messages.get("jgutils.despawn.null");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NOT_PLAYER = Messages.get("jgutils.notplayer");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender instanceof Player)
            if(sender.hasPermission("jgutils.despawn"))
                if(args.length == 1 || args.length == 2){
                    Player p = (Player) sender;
                    List<LivingEntity> es  = p.getLocation().getWorld().getLivingEntities();
                    
                    int d;
                    try{
                        d = Integer.valueOf(args[1]);
                    }catch(ArrayIndexOutOfBoundsException aiobe){
                        d = JGutils.getDespawnRadius();
                    }
                    if(d > 200) d = 200;
                    
                    EntityType et;
                    try{
                         et = EntityType.valueOf(args[0].toUpperCase());
                    }catch(IllegalArgumentException e){
                        p.sendMessage(NULL_MESSAGE.replace("%e%", args[0])); return;
                    }
                    
                    int n = 0;
                    for(LivingEntity e: es){
                        if(e.getLocation().distance(p.getLocation()) <= d &&
                                e.getType() == et){ e.remove(); n++; }
                    }
                    p.sendMessage(DESPAWN_MESSAGE.replace("%n%", String.valueOf(n)));
                    
                }else sender.sendMessage(USAGE_MESSAGE);
           else sender.sendMessage(NO_PERM);
        else sender.sendMessage(NOT_PLAYER);
    }
}

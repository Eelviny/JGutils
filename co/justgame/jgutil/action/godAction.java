package co.justgame.jgutil.action;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;

public class godAction {
    final static String ENABLE_MESSAGE = Messages.get("jgutils.god.enable");
    final static String OTHER_ENABLE_MESSAGE = Messages.get("jgutils.god.otherenable");
    final static String DISABLE_MESSAGE = Messages.get("jgutils.god.disable");
    final static String OTHER_DISABLE_MESSAGE = Messages.get("jgutils.god.otherdisable");
    final static String USAGE_MESSAGE = Messages.get("jgutils.god.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NOT_PLAYER = Messages.get("jgutils.notplayer");
    final static String NO_PLAYER = Messages.get("jgutils.noplayer");
    final static String MORE_PLAYER = Messages.get("jgutils.noplayer");
    
    @SuppressWarnings("deprecation")
    public static void fire(CommandSender sender, String[] args){
        
            if(args.length == 0){ 
                if(sender instanceof Player){
                    Player p = (Player) sender; 
                    if(sender.hasPermission("jgutils.god")){
                        if(godListener.isIngodMode(p)){
                            godListener.exitgodMode(p); p.sendMessage(DISABLE_MESSAGE);
                        }else{
                            godListener.entergodMode(p); p.sendMessage(ENABLE_MESSAGE);
                        }
                    }else sender.sendMessage(NO_PERM);  
                }else sender.sendMessage(NOT_PLAYER);     
            }else if(args.length == 1){
                if(sender.hasPermission("jgutils.god.other")){
                    List<Player> ps = Bukkit.matchPlayer(args[0]);
                    if(ps.size() == 1){
                        Player op = ps.get(0);
                        if(godListener.isIngodMode(op)){
                            godListener.exitgodMode(op); sender.sendMessage(OTHER_DISABLE_MESSAGE.replace("%p%", op.getName()));
                        }else{
                            godListener.entergodMode(op); sender.sendMessage(OTHER_ENABLE_MESSAGE.replace("%p%", op.getName()));
                        }
                    }else if(ps.size() == 0)
                        sender.sendMessage(NO_PLAYER);
                     else if(ps.size() > 1)
                        sender.sendMessage(MORE_PLAYER);
                }else sender.sendMessage(NO_PERM);  
            }else sender.sendMessage(USAGE_MESSAGE);
    }
}

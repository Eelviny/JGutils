package co.justgame.jgutil.action;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;

public class UnFreezeAction {
    final static String UN_FREEZE_MESSAGE = Messages.get("jgutils.unfreeze.message");
    final static String OTHER_UN_FREEZE_MESSAGE = Messages.get("jgutils.unfreeze.othermessage");
    final static String NOT_FREEZE_MESSAGE = Messages.get("jgutils.unfreeze.not");
    final static String USAGE_MESSAGE = Messages.get("jgutils.unfreeze.usage");
    
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NOT_PLAYER = Messages.get("jgutils.notplayer");
    final static String NO_PLAYER = Messages.get("jgutils.noplayer");
    final static String MORE_PLAYER = Messages.get("jgutils.noplayer");
    
    @SuppressWarnings("deprecation")
    public static void fire(CommandSender sender, String[] args){
        if(sender instanceof Player){
            if(args.length == 1){
                if(sender.hasPermission("jgutils.freeze")){
                    List<Player> ps = Bukkit.matchPlayer(args[0]);
                    if(ps.size() == 1){
                        Player op = ps.get(0);
                           if(FreezeListener.isFrozen(op)){
                               FreezeListener.melt(op);
                               sender.sendMessage(UN_FREEZE_MESSAGE.replace("%p%", op.getName()));
                               op.sendMessage(OTHER_UN_FREEZE_MESSAGE);
                           }else sender.sendMessage(NOT_FREEZE_MESSAGE.replace("%p%", op.getName()));
                    }else if(ps.size() == 0)
                        sender.sendMessage(NO_PLAYER);
                     else if(ps.size() > 1)
                        sender.sendMessage(MORE_PLAYER);
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(USAGE_MESSAGE);
        }else sender.sendMessage(NOT_PLAYER);
    }
}

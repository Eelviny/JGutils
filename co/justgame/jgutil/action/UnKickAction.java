package co.justgame.jgutil.action;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;


public class UnKickAction {
    final static String INFO_MESSAGE = Messages.get("jgutils.unkick.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.unkick.usage");
    final static String NOT = Messages.get("jgutils.unkick.not");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NO_PLAYER = Messages.get("jgutils.noplayer");
    final static String MORE_PLAYER = Messages.get("jgutils.noplayer");
    
    @SuppressWarnings("deprecation")
    public static void fire(CommandSender sender, String[] args){
        if(args.length == 1){
            if(sender.hasPermission("jgutils.rmplayer")){
                List<Player> ps = Bukkit.matchPlayer(args[0]);
                if(ps.size() == 1){
                    Player op = ps.get(0);
                    if(KickListener.iskicked(op)){
                        KickListener.unkick(op);
                        sender.sendMessage(INFO_MESSAGE.replace("%p%", op.getName()));
                    }else sender.sendMessage(NOT);     
                }else if(ps.size() == 0)
                    sender.sendMessage(NO_PLAYER);
                 else if(ps.size() > 1)
                    sender.sendMessage(MORE_PLAYER);
            }
        }else sender.sendMessage(USAGE_MESSAGE);
    }
}

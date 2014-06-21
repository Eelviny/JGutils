package co.justgame.jgutil.action;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;


public class KickAction {
    final static String INFO_MESSAGE = Messages.get("jgutils.kick.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.kick.usage");
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
                    KickListener.kick(op);
                    sender.sendMessage(INFO_MESSAGE.replace("%p%", op.getName()));
                }else if(ps.size() == 0)
                    sender.sendMessage(NO_PLAYER);
                 else if(ps.size() > 1)
                    sender.sendMessage(MORE_PLAYER);
            }
        }else sender.sendMessage(USAGE_MESSAGE);
    }
}

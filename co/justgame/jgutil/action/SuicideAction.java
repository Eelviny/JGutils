package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.deaths.DeathList;
import co.justgame.jgutil.resources.Messages;


public class SuicideAction {
    final static String USAGE_MESSAGE = Messages.get("jgutils.suicide.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NOT_PLAYER = Messages.get("jgutils.notplayer");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender instanceof Player){
            if(args.length == 0){
                if(sender.hasPermission("jgutils.suicide")){
                    DeathList.PlayRandomDeath((Player)sender);
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(USAGE_MESSAGE);
        }else sender.sendMessage(NOT_PLAYER);
    }
}

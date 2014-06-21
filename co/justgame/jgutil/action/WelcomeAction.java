package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;

public class WelcomeAction {
    
    final static String WELCOME_MESSAGE = Messages.get("jgutils.welcome.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.welcome.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.welcome"))
            if(args.length == 0)
                if(WELCOME_MESSAGE != null) Messages.sendMultilineMessage(sender, WELCOME_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
    
    public static void fire(Player p){
        if(WELCOME_MESSAGE != null) Messages.sendMultilineMessage(p, WELCOME_MESSAGE);
    }
}

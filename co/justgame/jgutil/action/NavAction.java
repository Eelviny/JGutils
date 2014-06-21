package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;

import co.justgame.jgutil.resources.Messages;

public class NavAction {
    final static String NAV_MESSAGE = Messages.get("jgutils.nav.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.nav.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.navigation"))
            if(args.length == 0)
                if(NAV_MESSAGE != null) Messages.sendMultilineMessage(sender, NAV_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

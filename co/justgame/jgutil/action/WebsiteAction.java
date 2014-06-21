package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;

import co.justgame.jgutil.resources.Messages;


public class WebsiteAction {
    final static String WEB_MESSAGE = Messages.get("jgutils.web.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.web.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.website"))
            if(args.length == 0)
                if(WEB_MESSAGE != null) Messages.sendMultilineMessage(sender, WEB_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;
import co.justgame.jgutil.resources.Messages;

public class HelpAction {
    final static String HELP_MESSAGE = Messages.get("jgutils.help.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.help.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.help"))
            if(args.length == 0)
                if(HELP_MESSAGE != null) Messages.sendMultilineMessage(sender, HELP_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

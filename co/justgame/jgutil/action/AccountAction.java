package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;
import co.justgame.jgutil.resources.Messages;

public class AccountAction {
    
    final static String ACOUNT_MESSAGE = Messages.get("jgutils.account.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.account.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.account"))
            if(args.length == 0)
                if(ACOUNT_MESSAGE != null) Messages.sendMultilineMessage(sender, ACOUNT_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

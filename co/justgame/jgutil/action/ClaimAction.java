package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;
import co.justgame.jgutil.resources.Messages;


public class ClaimAction {
    final static String CLAIM_MESSAGE = Messages.get("jgutils.claim.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.claim.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.claim"))
            if(args.length == 0)
                if(CLAIM_MESSAGE != null) Messages.sendMultilineMessage(sender, CLAIM_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;

import co.justgame.jgutil.resources.Messages;

public class RulesAction {
    final static String RULES_MESSAGE = Messages.get("jgutils.rules.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.rules.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.rules"))
            if(args.length == 0)
                if(RULES_MESSAGE != null) Messages.sendMultilineMessage(sender, RULES_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

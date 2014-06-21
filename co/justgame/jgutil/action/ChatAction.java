package co.justgame.jgutil.action;

import org.bukkit.command.CommandSender;
import co.justgame.jgutil.resources.Messages;

public class ChatAction {
    final static String CHAT_MESSAGE = Messages.get("jgutils.chat.message");
    final static String USAGE_MESSAGE = Messages.get("jgutils.chat.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.chat"))
            if(args.length == 0)
                if(CHAT_MESSAGE != null) Messages.sendMultilineMessage(sender, CHAT_MESSAGE);
            else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
}

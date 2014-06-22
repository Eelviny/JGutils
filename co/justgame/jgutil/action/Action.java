package co.justgame.jgutil.action;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Action {
    
    public static void fireAction(CommandSender sender, Command cmd, String[] args, String s){
        switch(s.toLowerCase()){
        case "welcome":
            WelcomeAction.fire(sender, args); break;
        case"help":
            HelpAction.fire(sender, args); break;
        case"rules":
            RulesAction.fire(sender, args); break;
        case"account":
            AccountAction.fire(sender, args); break;
        case"chat":
            ChatAction.fire(sender, args); break;
        case"claim":
            ClaimAction.fire(sender, args); break;
        case"navigation":
            NavAction.fire(sender, args); break;
        case"website":
            WebsiteAction.fire(sender, args); break;
        case"suicide":
            SuicideAction.fire(sender, args); break;
        case"kill":
            KillAction.fire(sender, args); break;
        case"fly":
            FlyAction.fire(sender, args); break;
        case"god":
            godAction.fire(sender, args); break;
        case"freeze":
            FreezeAction.fire(sender, args); break;
        case"unfreeze":
            UnFreezeAction.fire(sender, args); break;
        case"lookup":
            LookupAction.fire(sender, args); break;
        case"tp":
        case"tpaccept":
        case"tpdeny":
        case"tplist":
        case"tppos":
            TeleportAction.fire(sender, cmd, args); break;
        case"despawn":
            DespawnAction.fire(sender, args); break;
        case"rmplayer":
           KickAction.fire(sender, args); break;
        case"raplayer":
            UnKickAction.fire(sender, args); break;
        }
    }
}

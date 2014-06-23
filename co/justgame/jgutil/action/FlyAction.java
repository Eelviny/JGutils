package co.justgame.jgutil.action;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;


public class FlyAction {
    final static String ENABLE_MESSAGE = Messages.get("jgutils.fly.enable");
    final static String OTHER_ENABLE_MESSAGE = Messages.get("jgutils.fly.otherenable");
    final static String DISABLE_MESSAGE = Messages.get("jgutils.fly.disable");
    final static String OTHER_DISABLE_MESSAGE = Messages.get("jgutils.fly.otherdisable");
    final static String SET_MESSAGE = Messages.get("jgutils.fly.set");
    final static String OTHER_SET_MESSAGE = Messages.get("jgutils.fly.otherset");
    final static String USAGE_MESSAGE = Messages.get("jgutils.fly.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NOT_PLAYER = Messages.get("jgutils.notplayer");
    final static String NO_PLAYER = Messages.get("jgutils.noplayer");
    final static String MORE_PLAYER = Messages.get("jgutils.noplayer");
    
    @SuppressWarnings("deprecation")
    public static void fire(CommandSender sender, String[] args){
        if(args.length == 0){
            if(sender.hasPermission("jgutils.fly"))
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    if(p.getAllowFlight()){ p.setAllowFlight(false); p.sendMessage(DISABLE_MESSAGE);
                    }else{ p.setAllowFlight(true); p.sendMessage(ENABLE_MESSAGE);}
                }else{
                    sender.sendMessage(NOT_PLAYER);
                }
            else sender.sendMessage(NO_PERM);
        }else if(args.length == 1){
            if(sender instanceof Player){
                if(sender.hasPermission("jgutils.fly.other")){
                    Player p = (Player) sender;
                    double i = 0;
                    try{
                         i = (double) Integer.valueOf(args[0])/10;
                    }catch(NumberFormatException e){
                        if(sender.hasPermission("jgutils.fly.other")){
                            otherPlayerFly(sender, args); return;
                        }else sender.sendMessage(NO_PERM);
                    }
                    if(i > 1) i = 1;
                    if(!p.getAllowFlight()){p.setAllowFlight(true); p.sendMessage(ENABLE_MESSAGE);} 
                    p.setFlySpeed((float) i); p.sendMessage(SET_MESSAGE.replace("%i%", String.valueOf((int)(i*10))));
                }else sender.sendMessage(NO_PERM);
            }else{
                if(sender.hasPermission("jgutils.fly.other"))
                    otherPlayerFly(sender, args);
                else sender.sendMessage(NO_PERM);
            }
        }else if(args.length == 2){
            if(sender.hasPermission("jgutils.fly.other")){
                List<Player> ps = Bukkit.matchPlayer(args[0]);
                if(ps.size() == 1){
                    Player op = ps.get(0);
                    double i = 0;
                    try{
                         i = (double) Integer.valueOf(args[1])/10;
                    }catch(NumberFormatException e){
                        sender.sendMessage(USAGE_MESSAGE); return;
                    }
                    if(i > 1) i = 1;
                    System.out.println(i);
                    if(!op.getAllowFlight()){ 
                        op.setAllowFlight(true); 
                        sender.sendMessage(OTHER_ENABLE_MESSAGE.replace("%p%", op.getName()));
                    }
                    
                    op.setFlySpeed((float) i); 
                    sender.sendMessage(OTHER_SET_MESSAGE.replace("%i%", String.valueOf((int)(i*10)).replace("%p%", op.getName())));
                }else if(ps.size() == 0)
                    sender.sendMessage(NO_PLAYER);
                 else if(ps.size() > 1)
                    sender.sendMessage(MORE_PLAYER);
            }else sender.sendMessage(NO_PERM);
        }else sender.sendMessage(USAGE_MESSAGE);
    }
    
    @SuppressWarnings("deprecation")
    private static void otherPlayerFly(CommandSender sender, String[] args){
        List<Player> ps = Bukkit.matchPlayer(args[0]);
        if(ps.size() == 1){
            Player op = ps.get(0);
            if(op.getAllowFlight()){ op.setAllowFlight(false); 
            sender.sendMessage(OTHER_DISABLE_MESSAGE.replace("%p%", op.getName()));
            }else{ op.setAllowFlight(true); sender.sendMessage(OTHER_ENABLE_MESSAGE.replace("%p%", op.getName()));}
        }else if(ps.size() == 0)
            sender.sendMessage(NO_PLAYER);
         else if(ps.size() > 1)
             sender.sendMessage(MORE_PLAYER);
    }
}

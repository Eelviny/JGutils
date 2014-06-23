package co.justgame.jgutil.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.main.JGutils;
import co.justgame.jgutil.resources.Messages;


public class TeleportAction {
    final static String TP_USAGE = Messages.get("jgutils.tp.usage.tp");
    final static String TPA_USAGE = Messages.get("jgutils.tp.usage.tpa");
    final static String TPD_USAGE = Messages.get("jgutils.tp.usage.tpd");
    final static String TPL_USAGE = Messages.get("jgutils.tp.usage.tpl");
    final static String TPP_USAGE = Messages.get("jgutils.tp.usage.tpp");
    
    final static String SEND_MESSAGE = Messages.get("jgutils.tp.send");
    final static String TO_OTHER_SEND_MESSAGE = Messages.get("jgutils.tp.toothersend");
    final static String FROM_OTHER_SEND_MESSAGE = Messages.get("jgutils.tp.fromothersend");
    final static String ME_MESSAGE = Messages.get("jgutils.tp.me");
    
    final static String ACCEPT_MESSAGE = Messages.get("jgutils.tp.accept");
    final static String TO_OTHER_ACCEPT_MESSAGE = Messages.get("jgutils.tp.tootheraccept");
    final static String FROM_OTHER_ACCEPT_MESSAGE = Messages.get("jgutils.tp.fromotheraccept");
    
    final static String DENY_MESSAGE = Messages.get("jgutils.tp.deny");
    final static String OTHER_DENY_MESSAGE = Messages.get("jgutils.tp.otherdeny");
    
    final static String NONE = Messages.get("jgutils.tp.none");
    
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NO_PLAYER = Messages.get("jgutils.existnoplayer");
    final static String NOT_PLAYER = Messages.get("jgutils.notplayer");
    final static String MORE_PLAYER = Messages.get("jgutils.noplayer");
    
    static HashMap<Player , ArrayList<Request>> pendingRequests = new HashMap<Player , ArrayList<Request>>();
    
    public static void fire(CommandSender sender, Command cmd, String[] args){
        if(cmd.getName().equalsIgnoreCase("tp")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(sender.hasPermission("jgutils.teleport")){
                    if(args.length == 1){
                        Player to  = args[0].equalsIgnoreCase("me") ? p : findMatch(args[0], sender);
                        if(to != null){
                            if(sender.hasPermission("jgutils.teleport.norequest")){
                                p.teleport(to.getLocation());
                            }else{
                                if(pendingRequests.containsKey(to))
                                    pendingRequests.get(to).add(new Request(p, to));
                                else{
                                    pendingRequests.put(to, new ArrayList<Request>());
                                    pendingRequests.get(to).add(new Request(p, to));
                                }
                                sender.sendMessage(SEND_MESSAGE.replace("%p%", to.getName()));
                                to.sendMessage(FROM_OTHER_SEND_MESSAGE.replace("%p%", p.getName()));
                            }
                        }else return;
                    }else if(args.length == 2){
                        Player going  = args[0].equalsIgnoreCase("me") ? p : findMatch(args[0], sender);
                        Player to  = args[1].equalsIgnoreCase("me") ? p : findMatch(args[1], sender);
                        
                        if(going != null && to != null){
                            if(going == p || to == p){
                                if(sender.hasPermission("jgutils.teleport.norequest")){
                                    going.teleport(to.getLocation());
                                }else{
                                    Player other = going == p ? to : going;
                                    if(pendingRequests.containsKey(other))
                                        pendingRequests.get(other).add(new Request(going, to));
                                    else{
                                        pendingRequests.put(other, new ArrayList<Request>());
                                        pendingRequests.get(other).add(new Request(going, to));
                                    }
                                    sender.sendMessage(SEND_MESSAGE.replace("%p%", other.getName()));
                                    if(other == going) 
                                        other.sendMessage(TO_OTHER_SEND_MESSAGE.replace("%p%", p.getName()));
                                    else other.sendMessage(FROM_OTHER_SEND_MESSAGE.replace("%p%", p.getName()));
                                }
                            }else sender.sendMessage(ME_MESSAGE);
                        }else return;
                    }else sender.sendMessage(TP_USAGE);
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(NOT_PLAYER);
        }else if(cmd.getName().equalsIgnoreCase("tpaccept")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(sender.hasPermission("jgutils.teleport")){
                    if(args.length == 0){
                        if(pendingRequests.containsKey(p) && !pendingRequests.get(p).isEmpty()){
                            Request r = pendingRequests.get(p).get(0);
                            r.getGoing().teleport(r.getTo().getLocation());
                            pendingRequests.get(p).remove(r);
                            Player other = r.getGoing() == p ? r.getTo() : r.getGoing();
                            other.sendMessage(ACCEPT_MESSAGE.replace("%p%", p.getName()));
                            if(p == r.getGoing()) 
                                p.sendMessage(TO_OTHER_ACCEPT_MESSAGE.replace("%p%", other.getName()));
                            else p.sendMessage(FROM_OTHER_ACCEPT_MESSAGE.replace("%p%", other.getName()));
                        }else sender.sendMessage(NONE);
                    }else sender.sendMessage(TPA_USAGE);
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(NOT_PLAYER);
        }else if(cmd.getName().equalsIgnoreCase("tpdeny")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(sender.hasPermission("jgutils.teleport")){
                    if(args.length == 0){
                        if(pendingRequests.containsKey(p) && !pendingRequests.get(p).isEmpty()){
                            Request r = pendingRequests.get(p).get(0);
                            pendingRequests.get(p).remove(r);
                            Player other = r.getGoing() == p ? r.getTo() : r.getGoing();
                            other.sendMessage(DENY_MESSAGE.replace("%p%", p.getName()));
                            p.sendMessage(OTHER_DENY_MESSAGE.replace("%p%", other.getName()));
                        }else sender.sendMessage(NONE);
                    }else sender.sendMessage(TPD_USAGE);
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(NOT_PLAYER);
        }else if(cmd.getName().equalsIgnoreCase("tplist")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(sender.hasPermission("jgutils.teleport")){
                    if(args.length == 0){
                        if(pendingRequests.containsKey(p) && !pendingRequests.get(p).isEmpty()){
                           sender.sendMessage("§2 Pending Requests:");
                           if(pendingRequests.get(p).isEmpty())  sender.sendMessage("§cNo Pending Requests");
                           for(Request r: pendingRequests.get(p)){
                               sender.sendMessage(("&§   -§3"+r.getGoing().getName()+" to "+r.getTo().getName())
                                       .replace(p.getName(), "You"));
                           }
                        }else sender.sendMessage(NONE);
                    }else sender.sendMessage(TPL_USAGE);
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(NOT_PLAYER);
        }else if(cmd.getName().equalsIgnoreCase("tppos")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(sender.hasPermission("jgutils.teleport.tppos")){
                    try{
                        Location l = p.getLocation();
                    int X = args[0].contains("~") ? Integer.valueOf(args[0].replace("~", "0"))+(int)l.getX() : Integer.valueOf(args[0]);
                    int Y = args[1].contains("~") ? Integer.valueOf(args[1].replace("~", "0"))+(int)l.getY() : Integer.valueOf(args[1]);
                    int Z = args[2].contains("~") ? Integer.valueOf(args[2].replace("~", "0")+(int)l.getZ()) : Integer.valueOf(args[2]);
                    
                    p.teleport(new Location(Bukkit.getWorld(args[3]), X, Y, Z).add(.5, 0, .5));
                    
                    }catch(Exception e){ sender.sendMessage(TPP_USAGE);
                    e.printStackTrace();}
                }else sender.sendMessage(NO_PERM);
            }else sender.sendMessage(NOT_PLAYER);
        }
    }
    
    public static void startLoop(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JGutils.getInstance(), new Runnable(){
            @Override
            public void run(){
               for(Player p : pendingRequests.keySet()){
                   Iterator<Request> ir = pendingRequests.get(p).iterator();
                   while(ir.hasNext()){
                       Request r = ir.next();
                       if(r.isdeleted()) ir.remove();
                   }
               }
            }
        }, 10, 10);
    }
    
    @SuppressWarnings("deprecation")
    private static Player findMatch(String p, CommandSender sender){
        List<Player> ps = Bukkit.matchPlayer(p);
        if(ps.size() == 1){
           return ps.get(0);
        }else if(ps.size() == 0){
            sender.sendMessage(NO_PLAYER.replace("%p%", p));
        }else if(ps.size() > 1)
            sender.sendMessage(MORE_PLAYER);
        return null;
    }
}

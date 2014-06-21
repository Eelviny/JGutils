package co.justgame.jgutil.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.justgame.jgutil.resources.Messages;

public class LookupAction {
    final static String USAGE_MESSAGE = Messages.get("jgutils.lookup.usage");
    final static String NO_PERM = Messages.get("jgutils.noperm");
    final static String NO_PLAYER = Messages.get("jgutils.noplayer");
    final static String MORE_PLAYER = Messages.get("jgutils.noplayer");
    
    @SuppressWarnings("deprecation")
    public static void fire(CommandSender sender, String[] args){
        if(sender.hasPermission("jgutils.lookup"))
            if(args.length == 1){
                List<Player> ps = Bukkit.matchPlayer(args[0]);
                if(ps.size() == 1){
                    Player op = ps.get(0);
                    Location loc = op.getLocation();
                    Messages.sendMultilineMessage(sender, toString(op.getName(), op.getUniqueId().toString(),
                                       loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getName(), 
                                       op.getGameMode().name(), op.getHealth(), op.getFoodLevel(),
                                       op.getSaturation(), op.getExpToLevel(),
                                       godListener.isIngodMode(op), op.getAllowFlight()));
                }else if(ps.size() == 0){
                    String s = PlayerFile.readPlayerFile(args[0]);
                    if(s != null) Messages.sendMultilineMessage(sender, s);
                    else sender.sendMessage(NO_PLAYER);
                }else if(ps.size() > 1)
                    sender.sendMessage(MORE_PLAYER);
            }else sender.sendMessage(USAGE_MESSAGE);
        else sender.sendMessage(NO_PERM);   
    }
    
    public static String toString(String name, String UUID, double X, double Y, double Z, String world, String gm, double life, 
            int hunger, float saturation, int level, boolean godMode, boolean fly){
        StringBuilder s = new StringBuilder("§2" + StringUtils.capitalize(name) + ":/n" +
                                            "§8   UUID:§3 " + UUID + "/n" +
                                            "§8   Location:/n" +
                                            "§7      X:§3 " +X+ "/n" +
                                            "§7      Y:§3 " +Y+ "/n" +
                                            "§7      Z:§3 " +Z+ "/n" +
                                            "§7      World:§3 " +world+ "/n" +
                                            "§8   GameMode:§3 " +StringUtils.capitalize(gm.toLowerCase())+ "/n"+
                                            "§8   godMode:§3 " +StringUtils.capitalize(String.valueOf(godMode))+ "/n"+
                                            "§8   FlyMode:§3 " +StringUtils.capitalize(String.valueOf(fly))+ "/n"+
                                            "§8   Level:§3 " +level+ "/n");
        
        if(!gm.equalsIgnoreCase("CREATIVE")) s.append("§8   Health:§3 " +life+ "/n"+
                                                      "§8   Hunger:§3 " +hunger+ "/n"+
                                                      "§8   Saturation:§3 " +saturation+ "/n");
        
        return s.toString();
        
    }
}

package co.justgame.jgutil.action;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LookupLogoutListener implements Listener{
    @EventHandler(priority = EventPriority.NORMAL)
    public void logoutEvent(PlayerQuitEvent e){
        PlayerFile.savePlayerFile(e.getPlayer());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void kickEvent(PlayerKickEvent e){
        PlayerFile.savePlayerFile(e.getPlayer());
    }

}

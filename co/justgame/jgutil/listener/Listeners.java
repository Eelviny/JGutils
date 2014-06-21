package co.justgame.jgutil.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.justgame.jgutil.action.WelcomeAction;


public class Listeners implements Listener{  
    @EventHandler(priority = EventPriority.NORMAL)
    public void onLogin(PlayerJoinEvent e){
        WelcomeAction.fire(e.getPlayer());
    }
}

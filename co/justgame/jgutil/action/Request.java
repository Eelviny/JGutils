package co.justgame.jgutil.action;

import org.bukkit.entity.Player;

import co.justgame.jgutil.main.JGutils;


public class Request {
    
    final int i = JGutils.getTimeOut();
    private Player going;
    private Player to;
    private boolean deleted;
    
    public Request(Player going, Player to){
        this.going = going;
        this.to = to;
        startLife();
    }
    
    public synchronized boolean isdeleted(){
        return deleted;
    }
    
    public synchronized void setdeleted(){
       deleted = true;
    }
    
    public Player getGoing(){
        return going;
    }
    
    public Player getTo(){
        return to;
    }
    
    private void startLife(){
        Thread t = new Thread(){
            public void run(){
                try{
                    Thread.sleep(i*1000);
                    setdeleted();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

}

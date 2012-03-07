package org.kitteh.softlimit;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;

public class SoftLimit extends JavaPlugin implements Listener{
    private String kickMessage;
    
    @Override
    public void onEnable(){
        this.getConfig().options().copyDefaults(true);
        this.kickMessage=this.getConfig().getString("kickmsg","&&cServer full! &&f:(").replace("&&", String.valueOf(ChatColor.COLOR_CHAR));
        this.saveConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler (priority=EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent event) {
        if (event.getResult() == Result.KICK_FULL) {
            if(event.getPlayer().hasPermission("softlimit.bypass")){
                event.allow();
            } else {
                event.setKickMessage(kickMessage);
            }
        }
    }
}

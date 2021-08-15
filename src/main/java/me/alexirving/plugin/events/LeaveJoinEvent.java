package me.alexirving.plugin.events;

import me.alexirving.plugin.AdvancedSetups;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveJoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        AdvancedSetups.getInstance().editMode.put(event.getPlayer(),false);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        AdvancedSetups.getInstance().editMode.put(event.getPlayer(),false);
    }
}

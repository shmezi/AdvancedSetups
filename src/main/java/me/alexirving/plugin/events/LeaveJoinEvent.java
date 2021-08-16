/*
 * Copyright (c) 2021. Alex Irving. (Alexirving992@gmail.com)
 * All contents of this project are copyrights of Alex Irving.
 *
 * Any modification, distribution or any form of copying is strictly prohibited without an explicit document showing otherwise!
 */
package me.alexirving.plugin.events;

import me.alexirving.plugin.AdvancedSetups;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveJoinEvent implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    AdvancedSetups.getInstance().editMode.put(event.getPlayer(), false);
  }

  @EventHandler
  public void onLeave(PlayerQuitEvent event) {
    AdvancedSetups.getInstance().editMode.put(event.getPlayer(), false);
  }
}

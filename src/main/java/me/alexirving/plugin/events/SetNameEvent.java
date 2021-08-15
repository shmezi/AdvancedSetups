package me.alexirving.plugin.events;

import me.alexirving.plugin.AdvancedSetups;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SetNameEvent implements Listener {
  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    AdvancedSetups setups = AdvancedSetups.getInstance();
    if (setups.editMode.get(event.getPlayer())) {
      setups
          .getConfig()
          .set(
              "Placeholders." + setups.editing.get(event.getPlayer()) + ".Value",
              event.getMessage());
      event
          .getPlayer()
          .sendMessage(
              ChatColor.translateAlternateColorCodes(
                  '&',
                  setups
                      .getConfig()
                      .getString("GuiSettings.PlaceholderDone")
                      .replaceAll("%placeholder%", setups.editing.get(event.getPlayer()))
                      .replaceAll("%set-to%", event.getMessage())));
      setups.saveConfig();
      setups.editMode.put(event.getPlayer(), false);
      setups.editing.remove(event.getPlayer());
      event.setCancelled(true);
    }
  }
}

/*
 * Copyright (c) 2021. Alex Irving. (Alexirving992@gmail.com)
 * All contents of this project are copyrights of Alex Irving.
 *
 * Any modification, distribution or any form of copying is strictly prohibited without an explicit document showing otherwise!
 */
package me.alexirving.plugin.events;

import me.alexirving.plugin.AdvancedSetups;
import me.alexirving.plugin.utils.Type;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.io.IOException;

import static me.alexirving.plugin.utils.Type.FILE;
import static me.alexirving.plugin.utils.Type.PLACEHOLDER;

public class SetNameEvent implements Listener {
  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) throws IOException {
    AdvancedSetups setups = AdvancedSetups.getInstance();
    String editing = setups.editing.get(event.getPlayer());
    if (setups.editMode.get(event.getPlayer())) {
      if (Type.valueOf(setups.getConfig().getString("Placeholders." + editing + ".DATA.Type"))
          .equals(PLACEHOLDER)) {
        setups
            .getConfig()
            .set(
                "Placeholders." + setups.editing.get(event.getPlayer()) + ".Value",
                event.getMessage());
        setups.saveConfig();
        setups.editMode.put(event.getPlayer(), false);
        setups.editing.remove(event.getPlayer());
        event.setCancelled(true);
        System.out.println("FILE!!");
      } else if (Type.valueOf(
              setups.getConfig().getString("Placeholders." + editing + ".DATA.Type"))
          .equals(FILE)) {
        System.out.println("FILE!!");
        File configFile =
            new File(
                setups
                    .getConfig()
                    .getString("Placeholders." + editing + ".DATA.File")
                    .replaceAll("%data%", setups.getDataFolder().getAbsolutePath()));
        FileConfiguration editable = YamlConfiguration.loadConfiguration(configFile);
        AdvancedSetups.getInstance().getLogger().info(editable.getString("wow"));
        editable.set(
            setups.getConfig().getString("Placeholders." + editing + ".DATA.Variable"),
            event.getMessage());

        editable.save(configFile);
      }
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
    }
  }
}

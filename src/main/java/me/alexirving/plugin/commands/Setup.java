package me.alexirving.plugin.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.alexirving.plugin.AdvancedSetups;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Setup implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      FileConfiguration config = AdvancedSetups.getInstance().getConfig();
      AdvancedSetups setups = AdvancedSetups.getInstance();

      ItemBuilder next =
          ItemBuilder.from(Material.matchMaterial(config.getString("GuiSettings.Next.Material")))
              .name(
                  Component.text(
                      ChatColor.translateAlternateColorCodes(
                          '&', setups.getConfig().getString("GuiSettings.Next.Name"))));
      ItemBuilder prev =
          ItemBuilder.from(
                  Material.matchMaterial(config.getString("GuiSettings.Previous.Material")))
              .name(
                  Component.text(
                      ChatColor.translateAlternateColorCodes(
                          '&', setups.getConfig().getString("GuiSettings.Previous.Name"))));
      PaginatedGui gui =
          Gui.paginated()
              .title(
                  Component.text(
                      ChatColor.translateAlternateColorCodes(
                          '&', setups.getConfig().getString("GuiSettings.Title"))))
              .rows(6)
              .pageSize(45)
              .disableAllInteractions()
              .create();
      gui.setItem(6, 3, prev.asGuiItem(event -> gui.previous()));
      gui.setItem(6, 7, next.asGuiItem(event -> gui.next()));
      for (String placeholder : config.getConfigurationSection("Placeholders").getKeys(false)) {
        ItemBuilder builder =
            ItemBuilder.from(
                    Material.matchMaterial(
                        config.getString("Placeholders." + placeholder + ".Material")))
                .name(
                    LegacyComponentSerializer.legacyAmpersand()
                        .deserialize(config.getString("Placeholders." + placeholder + ".Name")));
        gui.addItem(
            builder.asGuiItem(
                event -> {
                  AdvancedSetups.getInstance().editMode.put(player, true);
                  AdvancedSetups.getInstance().editing.put(player, placeholder);
                  player.sendMessage(
                      ChatColor.translateAlternateColorCodes(
                          '&',
                          config
                              .getString("GuiSettings.PlaceholderSet")
                              .replaceAll("%placeholder%", placeholder)));
                  gui.close(event.getWhoClicked());
                }));
        gui.open(player);
      }
    } else {
      Bukkit.getLogger().warning("Please run this command in-game!");
    }
    return true;
  }
}

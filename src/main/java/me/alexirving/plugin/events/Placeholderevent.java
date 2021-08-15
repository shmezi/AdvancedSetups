package me.alexirving.plugin.events;

import me.alexirving.plugin.AdvancedSetups;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class Placeholderevent extends PlaceholderExpansion {
  FileConfiguration config = AdvancedSetups.getInstance().getConfig();

  @Override
  public @NotNull String getIdentifier() {
    return config.getString("GuiSettings.PlaceholderId");
  }

  @Override
  public @NotNull String getAuthor() {
    return "Alex Irving | Java developer.";
  }

  @Override
  public @NotNull String getVersion() {
    return "1.0";
  }

  @Override
  public String onRequest(OfflinePlayer player, String param) {
    String finalPlaceholder = "Error 404 Placeholder not found!";
    for (String placeholder : config.getConfigurationSection("Placeholders").getKeys(false)) {
      if (param.equals(placeholder)) {
        finalPlaceholder = config.getString("Placeholders." + placeholder + ".Value");
      }
    }
    return finalPlaceholder;
  }
}

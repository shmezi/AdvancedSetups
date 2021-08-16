/*
 * Copyright (c) 2021. Alex Irving. (Alexirving992@gmail.com)
 * All contents of this project are copyrights of Alex Irving.
 *
 * Any modification, distribution or any form of copying is strictly prohibited without an explicit document showing otherwise!
 */
package me.alexirving.plugin;

import me.alexirving.plugin.commands.Reload;
import me.alexirving.plugin.commands.Setup;
import me.alexirving.plugin.events.LeaveJoinEvent;
import me.alexirving.plugin.events.Placeholderevent;
import me.alexirving.plugin.events.SetNameEvent;
import me.alexirving.plugin.utils.AdvancedLicense;
import me.alexirving.plugin.utils.Colors;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.util.HashMap;

public final class AdvancedSetups extends JavaPlugin {
  private BukkitAudiences adventure;
  private static AdvancedSetups setups;
  public HashMap<Player, Boolean> editMode = new HashMap<>();
  public HashMap<Player, String> editing = new HashMap<>();

  public @NonNull BukkitAudiences adventure() {
    if (this.adventure == null) {
      throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
    }
    return this.adventure;
  }

  @Override
  public void onEnable() {
    this.adventure = BukkitAudiences.create(this);
    setups = this;

    // Config stuff
    saveDefaultConfig();
    File file = new File(this.getDataFolder().getPath(), "key.yml");
    if (!file.exists()) {
      saveResource("key.yml", false);
      getServer().shutdown();
    } else {

      Bukkit.getPluginManager().registerEvents(new LeaveJoinEvent(), this);
      Bukkit.getPluginManager().registerEvents(new SetNameEvent(), this);
      new Placeholderevent().register();
      FileConfiguration config =
          YamlConfiguration.loadConfiguration(new File(getDataFolder(), "key.yml"));
      if (!new AdvancedLicense(config.getString("key"), "https://serversetups.net/verify.php", this)
          .register()) {
        this.getLogger()
            .info(
                Colors.ANSI_RED
                    + "You licence key is invalid! please insert a valid key into the config!");
      }
      this.getCommand("setup").setExecutor(new Setup());
      this.getCommand("advancedsetupsreload").setExecutor(new Reload());
    }
  }

  @Override
  public void onDisable() {
    if (this.adventure != null) {
      this.adventure.close();
      this.adventure = null;
    }
  }

  public static AdvancedSetups getInstance() {
    return setups;
  }
}

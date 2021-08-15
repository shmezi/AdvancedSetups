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
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

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
    saveDefaultConfig();
    Bukkit.getPluginManager().registerEvents(new LeaveJoinEvent(), this);
    Bukkit.getPluginManager().registerEvents(new SetNameEvent(), this);
    new Placeholderevent().register();

    if (!new AdvancedLicense(
            getConfig().getString("key"), "https://serversetups.net/verify.php", this)
        .register()) {
      this.getLogger()
          .info(
              Colors.ANSI_RED
                  + "You licence key is invalid! please insert a valid key into the config!");
    }
    this.getCommand("setup").setExecutor(new Setup());
    this.getCommand("advancedsetupsreload").setExecutor(new Reload());
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

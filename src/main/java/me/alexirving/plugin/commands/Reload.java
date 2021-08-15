package me.alexirving.plugin.commands;

import me.alexirving.plugin.AdvancedSetups;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    AdvancedSetups.getInstance().reloadConfig();
    return false;
  }
}

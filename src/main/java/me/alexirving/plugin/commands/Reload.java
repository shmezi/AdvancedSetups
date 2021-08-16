/*
 * Copyright (c) 2021. Alex Irving. (Alexirving992@gmail.com)
 * All contents of this project are copyrights of Alex Irving.
 *
 * Any modification, distribution or any form of copying is strictly prohibited without an explicit document showing otherwise!
 */
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

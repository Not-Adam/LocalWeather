package me.adam.localweather.commands;

import org.bukkit.command.CommandSender;

public interface PluginCommand {
    String getName();

    String getPermission();

    String getUsage();

    String getDescription();

    boolean isPlayerOnly();

    void onCommand(CommandSender var1, String[] var2);
}

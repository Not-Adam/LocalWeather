package me.adam.localweather.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.adam.localweather.LocalWeather.PREFIX;
import static me.adam.localweather.LocalWeather.updatePlayer;
import static me.adam.localweather.utils.ConfigUtils.isLWEnabled;
import static me.adam.localweather.utils.translateHexColorCodes.translateHexColorCodes;

public class UpdateCommand implements PluginCommand {
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getPermission() {
        return "group.default";
    }

    @Override
    public String getUsage() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Updates the players weather and/or time!";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    public void onCommand(CommandSender sender, String[] var2) {
        Player player = (Player) sender;
        if (isLWEnabled(player)) {
            updatePlayer(player);
        } else {
            player.sendMessage(translateHexColorCodes(PREFIX + "LocalWeather must be enabled for you! /lw toggle on"));
        }
    }
}

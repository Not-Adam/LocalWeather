package me.adam.localweather.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.adam.localweather.LocalWeather.updatePlayer;

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
        updatePlayer(player);
    }
}

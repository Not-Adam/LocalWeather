package me.adam.localweather.commands;

import me.adam.localweather.LocalWeather;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.adam.localweather.LocalWeather.PREFIX;
import static me.adam.localweather.utils.ConfigUtils.isLWEnabled;
import static me.adam.localweather.utils.translateHexColorCodes.translateHexColorCodes;

public class ToggleCommand implements PluginCommand {
    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String getPermission() {
        return "group.default";
    }

    @Override
    public String getUsage() {
        return "toggle [on|off]";
    }

    @Override
    public String getDescription() {
        return "Toggles Local Weather!";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        LocalWeather plugin = LocalWeather.getInstance();
        Player player = (Player) sender;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                plugin.getConfig().set("players." + player.getUniqueId(), true);
                plugin.saveConfig();
                player.sendMessage(translateHexColorCodes(PREFIX + "Enabled Local Weather for you!"));
            } else if (args[0].equalsIgnoreCase("off")) {
                plugin.getConfig().set("players." + player.getUniqueId(), false);
                plugin.saveConfig();
                player.sendMessage(translateHexColorCodes(PREFIX + "Disabled Local Weather for you!"));
            } else {
                sender.sendMessage(translateHexColorCodes(PREFIX + "Invalid usage! /lw " + getUsage()));
            }
            return;

        } else if (args.length == 0) {
            if (isLWEnabled(player)) {
                plugin.getConfig().set("players." + player.getUniqueId(), false);
                plugin.saveConfig();
                player.sendMessage(translateHexColorCodes(PREFIX + "Disabled Local Weather for you!"));
                return;
            }

            plugin.getConfig().set("players." + player.getUniqueId(), true);
            plugin.saveConfig();
            player.sendMessage(translateHexColorCodes(PREFIX + "Enabled Local Weather for you!"));
            return;
        }

        player.sendMessage(translateHexColorCodes(PREFIX + "Invalid usage! /lw " + getUsage()));
    }
}

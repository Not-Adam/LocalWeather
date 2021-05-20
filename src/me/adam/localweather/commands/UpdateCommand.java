package me.adam.localweather.commands;

import me.adam.localweather.utils.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import static me.adam.localweather.modules.Time.updateTime;
import static me.adam.localweather.modules.Weather.updateWeather;
import static me.adam.localweather.utils.MiscUtils.playSound;
import static me.adam.localweather.utils.MiscUtils.playSoundAll;

public class UpdateCommand implements PluginCommand {
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getPermission() {
        return null;
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
        try {
            updateWeather(player);
            updateTime(player);
            playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 0F);
            Chat.sendActionBar(player, "&#FF9300" + ChatColor.BOLD + "LW" + ChatColor.RESET + ChatColor.GRAY + "\nCurrent server time is " + (player.getWorld().getTime()/20 * 72 / 60 / 60 + 6) % 24 + " hours!");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

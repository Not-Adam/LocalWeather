package me.adam.localweather;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import static me.adam.localweather.Weather.updateWeather;
import static me.adam.localweather.utils.MiscUtils.playSoundAll;
import static me.adam.localweather.utils.translateHexColorCodes.translateHexColorCodes;

public class LocalWeather extends JavaPlugin {
    public static String PREFIX = "&#FF9300" + ChatColor.BOLD + "LW" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
    public static LocalWeather INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new me.adam.localweather.listeners.PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new me.adam.localweather.listeners.WeatherChangeListener(), this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.broadcastMessage(translateHexColorCodes(PREFIX + "Refreshing weather for each player! This process happens every 10 minutes!"));
            playSoundAll(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 0F);
            for (Player player : Bukkit.getOnlinePlayers()) {
                try {
                    updateWeather(player);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, 0L, 12000L);

        Bukkit.getLogger().info("§aPlugin enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("§cPlugin disabled!");
    }
}

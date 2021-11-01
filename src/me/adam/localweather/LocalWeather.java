package me.adam.localweather;

import me.adam.localweather.commands.CommandHandler;
import me.adam.localweather.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

import static me.adam.localweather.modules.Time.updateTime;
import static me.adam.localweather.modules.Weather.updateWeather;
import static me.adam.localweather.utils.ConfigUtils.*;
import static me.adam.localweather.utils.translateHexColorCodes.translateHexColorCodes;

public class LocalWeather extends JavaPlugin {
    public static String PREFIX = "&#FF9300" + ChatColor.BOLD + "LW" + ChatColor.DARK_GRAY + " | " + ChatColor.RESET;
    public static Map<Player, DateTimeZone> PlayerTimezones = new HashMap<>();
    public static Map<Player, DateTime[]> PlayerTimeStamps = new HashMap<Player, DateTime[]>();
    public static LocalWeather INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getConfig().addDefault("cooldown (in minutes)", 10);
        getConfig().addDefault("console-messages", true);
        getConfig().addDefault("action-bar", true);
        getConfig().options().copyDefaults(true);
        saveConfig();

        getCommand("localweather").setExecutor(new CommandHandler());

        getServer().getPluginManager().registerEvents(new me.adam.localweather.listeners.PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new me.adam.localweather.listeners.WeatherChangeListener(), this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.broadcastMessage(translateHexColorCodes(PREFIX + "Refreshing weather for each player! This process happens every " + getCoolDownTime() + " minutes!"));
            if (isConsoleMessagesEnabled()) Bukkit.getLogger().info("§6You can change the cooldown time in the plugin's config.yml file. You may also silence these Information messages in console through the same file.");

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (isLWEnabled(player)) updatePlayer(player);
            }
        }, 0L, getCoolDownTimeInTicks());

        if (isActionBarEnabled()) {
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (isLWEnabled(player)) Chat.sendActionBar(player, PREFIX + "Current server time is " + (player.getWorld().getTime()/20 * 72 / 60 / 60 + 6) % 24 + " hours!");
                }
            }, 0L, 20L);
        }

        Bukkit.getLogger().info("§aEnabled Local Weather!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("§cDisabled Local Weather!");
    }

    public static LocalWeather getInstance() {
        return INSTANCE;
    }

    public static void updatePlayer(Player player) {
        try {
            updateWeather(player);
            updateTime(player);
            // playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 0F);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

package me.adam.localweather.utils;

import me.adam.localweather.LocalWeather;
import org.bukkit.entity.Player;

public class ConfigUtils {
    public static int getCoolDownTime() {
        return (int) LocalWeather.getInstance().getConfig().get("cooldown (in minutes)", 10);
    }

    public static int getCoolDownTimeInTicks() {
        return (int) LocalWeather.getInstance().getConfig().get("cooldown (in minutes)", 10) * 20 * 60;
    }

    public static boolean isConsoleMessagesEnabled() {
        return (boolean) LocalWeather.getInstance().getConfig().get("console-messages", true);
    }

    public static boolean isActionBarEnabled() {
        return (boolean) LocalWeather.getInstance().getConfig().get("action-bar", true);
    }

    public static boolean isLWEnabled(Player player) {
        return (boolean) LocalWeather.getInstance().getConfig().get("players." + player.getUniqueId(), true);
    }
}

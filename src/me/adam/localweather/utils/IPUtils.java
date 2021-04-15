package me.adam.localweather.utils;

import org.bukkit.entity.Player;

public class IPUtils {
    public static String getIP(Player player) {
        String playerIP = player.getAddress().getAddress().getHostAddress();
        return playerIP;
    }

}

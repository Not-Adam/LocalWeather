package me.adam.localweather.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MiscUtils {
    public static void playSound(Player player, Sound sound, Float volume, Float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static void playSoundAll(Sound sound, Float volume, Float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) player.playSound(player.getLocation(), sound, volume, pitch);
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.adam.localweather.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static me.adam.localweather.LocalWeather.PREFIX;

public class Chat {
    private static final Pattern PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private static final char COLOR_CHAR = '§';

    public Chat() {
    }

    public static String translateHexColorCodes(String message) {
        Matcher matcher = PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 32);

        while(matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }

    public static String color(String str, Object... args) {
        return String.format(ChatColor.translateAlternateColorCodes('&', translateHexColorCodes(str.replace("%prefix%", PREFIX))), args);
    }

    public static void sendActionBar(Player player, String message, Object... args) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(message, args)));
    }

    public static List<String> colorList(List<String> list) {
        List<String> colored = new ArrayList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            String str = (String)var2.next();
            colored.add(color(str));
        }

        return colored;
    }
}

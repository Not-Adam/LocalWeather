package me.adam.localweather.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class translateHexColorCodes {

    private static final Pattern PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public static String translateHexColorCodes(String message) {
        Matcher matcher = PATTERN.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 32);

        while(matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }
}

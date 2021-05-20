package me.adam.localweather.modules;

import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import java.util.Calendar;

import static me.adam.localweather.LocalWeather.PlayerTimezones;

public class Time {
    public static void updateTime(Player player) throws ParseException {
        Calendar calendar = Calendar.getInstance(PlayerTimezones.get(player));

        int hours = calendar.getTime().getHours();
        int minutes = calendar.getTime().getMinutes();

        int currentHoursConverted = (hours*1000)-6000;
        int currentMinutesConverted = (minutes*10)-60;

        player.setPlayerTime(currentHoursConverted + currentMinutesConverted, false);
    }
}

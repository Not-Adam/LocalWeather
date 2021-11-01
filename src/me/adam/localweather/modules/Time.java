package me.adam.localweather.modules;

import org.bukkit.entity.Player;
import org.joda.time.DateTime;
import org.json.simple.parser.ParseException;

import static me.adam.localweather.LocalWeather.PlayerTimeStamps;
import static me.adam.localweather.LocalWeather.PlayerTimezones;

public class Time {
    public static void updateTime(Player player) throws ParseException {
        DateTime dt = new DateTime(PlayerTimezones.get(player));
        int hours = dt.getHourOfDay();
        int minutes = dt.getMinuteOfDay();

        int currentHoursConverted = (hours*1000)-6000;
        int currentMinutesConverted = (minutes*10)-60;

        DateTime[] sunTimes = PlayerTimeStamps.get(player);
        DateTime sunrise = sunTimes[0];
        DateTime sunset = sunTimes[1];

        int offsetHours = hours - sunset.getHourOfDay();
        int offsetMinutes = minutes - sunset.getMinuteOfDay();

        player.setPlayerTime(currentHoursConverted + currentMinutesConverted + (offsetHours * 1000L) - 6000 + (offsetMinutes * 10L) - 60, false);
    }
}

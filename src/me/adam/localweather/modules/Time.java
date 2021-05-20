package me.adam.localweather.modules;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static me.adam.localweather.utils.APIUtils.executePost;
import static me.adam.localweather.utils.IPUtils.getIP;

public class Time {
    public static void updateTime(Player player) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject timeAPIResponse = (JSONObject) parser.parse(executePost("http://worldtimeapi.org/api/ip/" + getIP(player)));

        String[] time = timeAPIResponse.get("datetime").toString().split("T")[1].split(".")[0].split(":");

        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);

        int currentHoursConverted = (hours*1000)-6000;
        int currentMinutesConverted = (minutes*10)-60;

        player.setPlayerTime(currentHoursConverted + currentMinutesConverted, false);
    }
}

package me.adam.localweather.modules;

import me.adam.localweather.LocalWeather;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static me.adam.localweather.LocalWeather.PlayerTimeStamps;
import static me.adam.localweather.LocalWeather.PlayerTimezones;
import static me.adam.localweather.utils.APIUtils.executePost;
import static me.adam.localweather.utils.IPUtils.getIP;
import static me.adam.localweather.utils.translateHexColorCodes.translateHexColorCodes;

public class Weather {
    public static void updateWeather(Player player) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject countryObject = (JSONObject) parser.parse(executePost("http://ip-api.com/json/" + getIP(player)));

        if (countryObject.get("status").toString().equalsIgnoreCase("success")) {
            String city = countryObject.get("city").toString();
            String timeZoneID = countryObject.get("timezone").toString();
            PlayerTimezones.put(player, DateTimeZone.forID(timeZoneID));
            JSONObject weatherObject = (JSONObject) parser.parse(executePost("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=29f7248a5c58c5be1678868dc8ddaa27"));

            if (weatherObject.get("cod").toString().equalsIgnoreCase("200")) {
                JSONArray weatherDataArray = (JSONArray) weatherObject.get("weather");
                JSONObject weatherObj = (JSONObject) weatherDataArray.get(0);
                String weather = weatherObj.get("main").toString();


                if (weather.equalsIgnoreCase("clear")) {
                    player.setPlayerWeather(WeatherType.CLEAR);
                    player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Success! Setting weather to clear!"));

                } else if (weather.equalsIgnoreCase("clouds")) {
                    player.setPlayerWeather(WeatherType.CLEAR);
                    player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Success! Setting weather to clear/cloudy!"));

                } else if (weather.equalsIgnoreCase("mist")) {
                player.setPlayerWeather(WeatherType.CLEAR);
                player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Success! Setting weather to clear/misty!"));

                } else if (weather.equalsIgnoreCase("rain")) {
                    player.setPlayerWeather(WeatherType.DOWNFALL);
                    player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Success! Setting weather to rainy!"));

                } else if (weather.equalsIgnoreCase("snow")) {
                    player.setPlayerWeather(WeatherType.DOWNFALL);
                    player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Success! Setting weather to rainy/snowy!"));

                } else {
                    player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Unknown weather! Please message adamm#1415 on discord!"));
                }

            } else {
                player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Was not able to find your weather type!"));
            }

            JSONArray sunTimesDataArray = (JSONArray) weatherObject.get("sys");
            PlayerTimeStamps.put(player, new DateTime[] {new DateTime(Long.parseLong(sunTimesDataArray.get(3).toString()), PlayerTimezones.get(player)), new DateTime(Long.parseLong(sunTimesDataArray.get(4).toString()), PlayerTimezones.get(player))});

        } else {
            player.sendMessage(translateHexColorCodes(LocalWeather.PREFIX + "Failed to find your location!"));
        }
    }
}

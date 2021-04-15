package me.adam.localweather.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
    public void weatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}

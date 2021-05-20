package me.adam.localweather.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.simple.parser.ParseException;

import static me.adam.localweather.modules.Weather.updateWeather;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws ParseException {
        Player player = event.getPlayer();
        updateWeather(player);
    }
}

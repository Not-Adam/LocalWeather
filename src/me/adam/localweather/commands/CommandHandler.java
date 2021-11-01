package me.adam.localweather.commands;

import me.adam.localweather.LocalWeather;

public class CommandHandler extends CommandManager {

    public CommandHandler() {
        super(LocalWeather.getInstance(), "LW", "&#FF9300", "&#FF9300");

        addCommand(new UpdateCommand());
        addCommand(new ToggleCommand());
    }
}
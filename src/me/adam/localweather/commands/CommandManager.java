package me.adam.localweather.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private final String commandName;
    private final String primaryColor;
    private final String secondaryColor;
    private final List<PluginCommand> commands;

    public CommandManager(JavaPlugin plugin, String commandName, ChatColor primaryColor, ChatColor secondaryColor) {
        this(plugin, commandName, primaryColor.toString(), secondaryColor.toString());
    }

    public CommandManager(JavaPlugin plugin, String commandName, String primaryColor, String secondaryColor) {
        this.commandName = commandName;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.commands = new ArrayList();
        plugin.getCommand(commandName).setExecutor(this);
        plugin.getCommand(commandName).setTabCompleter(this);
    }

    public String getCommandName() {
        return this.commandName;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public void addCommand(PluginCommand gameCommand) {
        this.commands.add(gameCommand);
    }

    public List<PluginCommand> getCommands() {
        return this.commands;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(String.format("§cNot enough arguments! Type '/%s help' for help", this.commandName));
            return true;
        } else {
            Iterator var5 = this.commands.iterator();

            PluginCommand command;
            do {
                if (!var5.hasNext()) {
                    sender.sendMessage(String.format("§cUnknown command! Type '/%s help' for help", this.commandName));
                    return true;
                }

                command = (PluginCommand)var5.next();
            } while(!args[0].equalsIgnoreCase(command.getName()));

            if (sender.hasPermission(command.getPermission())) {
                if (command.isPlayerOnly()) {
                    if (sender instanceof Player) {
                        command.onCommand(sender, this.cutByOne(args));
                    } else {
                        sender.sendMessage("§cOnly players may use this command!");
                    }
                } else {
                    command.onCommand(sender, this.cutByOne(args));
                }
            } else {
                sender.sendMessage("§cYou are not allowed to use this command!");
            }

            return true;
        }
    }

    private String[] cutByOne(String[] arr) {
        return (String[]) Arrays.copyOfRange(arr, 1, arr.length);
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        } else if (args.length == 1) {
            List<String> list = new ArrayList();
            this.commands.stream().filter((command) -> {
                return command.getName().startsWith(args[0]);
            }).forEach((command) -> {
                list.add(command.getName());
            });
            return list;
        } else {
            return null;
        }
    }
}

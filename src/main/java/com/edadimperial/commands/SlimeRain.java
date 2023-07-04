package com.edadimperial.commands;

import com.edadimperial.SlimeRainEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SlimeRain implements CommandExecutor {
    Plugin instance;

    public SlimeRain(Plugin plugin) {
        instance = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            SlimeRainEvent rain = new SlimeRainEvent(instance);
            rain.startRain();
            return true;
        }

        return true;
    }
}

package com.edadimperial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SlimeRainTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> subCommands = new ArrayList<>();
        subCommands.add("start");
        subCommands.add("stop");
        subCommands.add("reload");

        if (args.length > 0) {
            return subCommands;
        }
        return null;
    }
}

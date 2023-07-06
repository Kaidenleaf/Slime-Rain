package com.edadimperial;

import com.edadimperial.commands.SlimeRain;
import com.edadimperial.commands.SlimeRainTabComplete;
import com.edadimperial.listeners.DayListener;
import com.edadimperial.listeners.SlimeListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        saveDefaultConfig();
        getCommand("slimerain").setExecutor(new SlimeRain(this));
        getCommand("slimerain").setTabCompleter(new SlimeRainTabComplete());
        getServer().getPluginManager().registerEvents(new SlimeListener(), this);
        //DayListener listen = new DayListener(this);
    }

}
package com.edadimperial;

import com.edadimperial.commands.SlimeRain;
import com.edadimperial.listeners.SlimeListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        getCommand("slimerain").setExecutor(new SlimeRain(this));
        getServer().getPluginManager().registerEvents(new SlimeListener(), this);

    }

}
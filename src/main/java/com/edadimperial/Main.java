package com.edadimperial;

import com.edadimperial.commands.SlimeRain;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        getCommand("slimerain").setExecutor(new SlimeRain(this));
    }

}
package com.edadimperial.listeners;

import com.edadimperial.SlimeRainEvent;
import com.edadimperial.commands.SlimeRain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DayListener {
    Plugin instance;
    World world;
    public DayListener(Plugin plugin){
        this.instance = plugin;
        this.world = instance.getServer().getWorld("world");
        checkTime();
    }
    public void checkTime() {

        new BukkitRunnable() {
            public void run() {
                long time = world.getTime();
                if (time >= 1000L && time <= 2000L) {
                    executeEvent();
                }
            }
        }.runTaskTimer(instance, 0L, 20L); // Run the task every second (20 ticks);
    }

    private void executeEvent() {
        Bukkit.broadcastMessage("SLIMES!");
        //SlimeRainEvent rain = new SlimeRainEvent(instance);
        //rain.startRain(world);
    }
}

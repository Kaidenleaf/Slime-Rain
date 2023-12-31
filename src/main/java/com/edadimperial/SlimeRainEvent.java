package com.edadimperial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlimeRainEvent {
    Plugin instance;

    public SlimeRainEvent(Plugin plugin) {
        this.instance = plugin;
    }

    public void startRain(World world) {
        List<Player> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
        for (Player player : players) {
            if (player.getWorld() == world) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a¡Llueven Slimes!"));
            }
        }

        int duration = 120;
        int interval = 3;
        new BukkitRunnable() {
            int count = 0;

            public void run() {
                count++;
                List<Player> currentPlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());

                if (world != null && !world.hasStorm()) {
                    world.setStorm(true);
                }

                if (count >= duration / interval) {
                    for (Player player : currentPlayers) {
                        if (player.getWorld() == world) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSlimes han dejado de caer del cielo"));
                        }
                    }
                    if (world != null) {
                        world.setStorm(false);
                    }
                    cancel();
                }

                Collections.shuffle(currentPlayers);

                for (Player currentPlayer : currentPlayers) {
                    Location loc = currentPlayer.getLocation();
                    World pWorld = currentPlayer.getWorld();

                    if (pWorld.getName().equals("world")) {
                        int x = (int) (loc.getX() + (Math.random() * 20));
                        int y = (int) (loc.getY() + 100);
                        int z = (int) (loc.getZ() + (Math.random() * 20));
                        Location spawnLoc = new Location(pWorld, x, y, z);
                        Entity slime = pWorld.spawnEntity(spawnLoc, EntityType.SLIME);
                        slime.setPersistent(true);
                        slime.setCustomName("Slime");

                        // Schedule the entity to be removed after a certain amount of time
                        Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
                            public void run() {
                                slime.remove();
                            }
                        }, 20 * 60); // Remove the entity after 60 seconds
                    }
                }
            }
        }.runTaskTimer(instance, 0, interval * 20);

    }
}

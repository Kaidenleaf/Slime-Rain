package com.edadimperial.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlimeRain implements CommandExecutor {
    Plugin instance;
    public SlimeRain(Plugin plugin) {
        instance = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
        for (Player player : players) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"start rain"));
        }

        World world = instance.getServer().getWorld("world");

        int duration = 60;
        int interval = 5;
        new BukkitRunnable() {
            int count = 0;
            public void run() {
                count++;
                List<Player> currentPlayers = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());

                if (world != null && !world.hasStorm()) {
                    world.setStorm(true);
                }

                if (count >= duration/interval) {
                    for (Player player : currentPlayers) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEnd rain"));
                    }
                    if (world != null) {
                        world.setStorm(false);
                    }
                    cancel();
                }

                Collections.shuffle(currentPlayers);

                int numPlayers = currentPlayers.size();

                for (Player currentPlayer : currentPlayers) {
                    Location loc = currentPlayer.getLocation();
                    World pWorld = currentPlayer.getWorld();

                    // Spawn a group of slime mobs based on the number of players in the area

                    if (pWorld.getName().equals("world")) {
                        int x = (int) (loc.getX() + (Math.random() * 20));
                        int y = (int) (loc.getY() + 100);
                        int z = (int) (loc.getZ() + (Math.random() * 20));
                        Location spawnLoc = new Location(pWorld, x, y, z);
                        Entity slime = pWorld.spawnEntity(spawnLoc, EntityType.SLIME);
                        slime.setPersistent(true);
                        slime.setCustomName("Slime");
                        for (Player player : players) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Slime N" + count));
                        }

                        // Schedule the entity to be removed after a certain amount of time
                        Bukkit.getScheduler().runTaskLater(instance, new Runnable() {
                            public void run() {
                                slime.remove();
                            }
                        }, 20 * 60); // Remove the entity after 30 seconds


                    }
                }


            }
        }.runTaskTimer(instance, 0,interval * 20);

        return true;
    }
}

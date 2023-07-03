package com.edadimperial.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.SlimeSplitEvent;

public class SlimeListener implements Listener {
    @EventHandler
    public void onEntityDamage(SlimeSplitEvent event) {
        Entity slime = event.getEntity();
        if (slime.getCustomName() != null && slime.getCustomName().equals("Slime")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void cancelSlimeFallDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Slime && entity.getCustomName() != null && entity.getCustomName().equals("Slime") && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
}
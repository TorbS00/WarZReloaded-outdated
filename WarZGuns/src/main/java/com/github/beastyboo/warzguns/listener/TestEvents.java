package com.github.beastyboo.warzguns.listener;

import com.github.beastyboo.warzguns.WarZGuns;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestEvents implements Listener {

    private final WarZGuns core;
    private final Set<UUID> bullets;

    public TestEvents(WarZGuns core) {
        this.core = core;
        this.bullets = new HashSet<>();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }

        if(event.getItem().getType() != Material.DIAMOND_AXE) {
            return;
        }

        Player player = event.getPlayer();
        Snowball bullet = player.launchProjectile(Snowball.class, player.getLocation().getDirection());
        bullets.add(bullet.getUniqueId());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getDamager();
            UUID uuid = snowball.getUniqueId();

            if(!bullets.contains(uuid)) {
                return;
            }

            bullets.remove(uuid);

            if(!(event.getEntity() instanceof Player)) {
                return;
            }

            event.setDamage(11);

        }
    }
}

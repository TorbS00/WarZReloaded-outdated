package com.github.beastyboo.warzguns.listener;

import com.github.beastyboo.warzguns.WarZGuns;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;

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
        bullet.setShooter(event.getPlayer());

        //Example 15 blocks:

        /*
        Location fifteenBlocksAway = player.getLocation().add(player.getLocation().getDirection().multiply(100));
        Location playerLocation = player.getLocation();
        double vectorX = Math.pow(Math.sqrt(playerLocation.getX() - fifteenBlocksAway.getX()), 2);
        double vectorY = Math.pow(Math.sqrt(playerLocation.getY() - fifteenBlocksAway.getY()), 2);
        double vectorZ = Math.pow(Math.sqrt(playerLocation.getZ() - fifteenBlocksAway.getZ()), 2);
        Vector vector = new Vector(vectorX, vectorY, vectorZ);

        Vector direction = player.getLocation().getDirection().normalize();
        direction.multiply(15);

        bullet.setVelocity(direction);
         */

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




            event.setDamage(25);

        }
    }

    //Gun methods:



}

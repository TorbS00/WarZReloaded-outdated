package com.github.beastyboo.warzguns.gun.bullet;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class BulletHitListener implements Listener {

    private final WarZGuns core;

    public BulletHitListener(WarZGuns core) {
        this.core = core;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Snowball) {

            Snowball snowball = (Snowball) event.getDamager();
            UUID uuid = snowball.getUniqueId();
            Bullet bullet = core.getBullet(uuid);

            if(bullet == null) {
                return;
            }

            Entity entity = event.getEntity();

            double offsetY = snowball.getLocation().getY() - entity.getLocation().getY();

            boolean headshot = offsetY > 1.35d;
            boolean legshot = offsetY < 0.75d;

            Gun gun = bullet.getGun();
            double calculatedDamage = 0;

            if(headshot){
                calculatedDamage = gun.getDamagePerBullet() * (1 + (gun.getHeadShotIncrease() / 100));
            }
            else if(legshot) {
                calculatedDamage = gun.getDamagePerBullet() * (1 - (gun.getLegShotDecrease() / 100));
            }
            else {
                calculatedDamage = gun.getDamagePerBullet();
            }
            event.setDamage(calculatedDamage);
        }
    }
}

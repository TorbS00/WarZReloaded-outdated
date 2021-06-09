package com.github.beastyboo.warzguns.gun.bullet;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

public class BulletFactory {

    private final WarZGuns core;
    private final BulletTracker bulletTracker;

    public BulletFactory(WarZGuns core) {
        this.core = core;
        bulletTracker = new BulletTracker();
    }

    public void createBullet(Player player, Gun gun) {
        Vector vector = player.getLocation().getDirection();
        Vector newVector = null;

        if(player.isSneaking()) {
            newVector = core.getAccuracyCalculator().calculateAccuracy(vector, gun.getAccuracy_crouched());
        } else {
            newVector = core.getAccuracyCalculator().calculateAccuracy(vector, gun.getAccuracy());
        }

        double blocksPerTick = gun.getBulletSpeed() / 20;

        Snowball snowball = player.launchProjectile(Snowball.class, newVector.normalize().multiply(blocksPerTick));
        snowball.setShooter(player);

        Bullet bullet = new Bullet(snowball.getUniqueId(), gun);
        bulletTracker.addBullet(bullet);
    }

    public BulletTracker getBulletTracker() {
        return bulletTracker;
    }
}

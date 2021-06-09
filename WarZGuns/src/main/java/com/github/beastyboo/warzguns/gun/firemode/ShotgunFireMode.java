package com.github.beastyboo.warzguns.gun.firemode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import com.github.beastyboo.warzguns.gun.firemode.adapter.ShotgunFireAdapter;
import com.google.gson.annotations.JsonAdapter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

import java.util.UUID;

@JsonAdapter(ShotgunFireAdapter.class)
public class ShotgunFireMode implements IFireMode {

    private final int bulletsPerRound;

    public ShotgunFireMode(int bulletsPerRound) {
        this.bulletsPerRound = bulletsPerRound;
    }

    @Override
    public String identifier() {
        return "shotgun";
    }

    @Override
    public boolean executeGunShot(WarZGuns core, Player player, Gun gun) {
        UUID uuid = player.getUniqueId();
        if(core.getGunDelayMap().containsKey(uuid)) {
            if(System.currentTimeMillis() < core.getGunDelayMap().get(uuid)) {
                return false;
            }
        }

        for(int i=0; i<bulletsPerRound; i++) {
            Vector vector = player.getLocation().getDirection();
            Vector newVector = null;

            if(player.isSneaking()) {
                newVector = core.getAccuracyCalculator().calculateAccuracy(vector, gun.getAccuracy_crouched());
            } else {
                newVector = core.getAccuracyCalculator().calculateAccuracy(vector, gun.getAccuracy());
            }

            Snowball bullet = player.launchProjectile(Snowball.class, newVector);
            bullet.setShooter(player);
        }

        core.getGunDelayMap().put(uuid, System.currentTimeMillis() + gun.getDelay());
        return true;
    }

    public int getBulletsPerRound() {
        return bulletsPerRound;
    }

    @Override
    public String toString() {
        return "ShotgunFireMode{" +
                "bulletsPerRound=" + bulletsPerRound +
                '}';
    }
}

package com.github.beastyboo.warzguns.gun.firemode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import com.github.beastyboo.warzguns.gun.firemode.adapter.BurstFireAdapter;
import com.google.gson.annotations.JsonAdapter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

@JsonAdapter(BurstFireAdapter.class)
public class BurstFireMode implements IFireMode {

    private final int bulletsPerBurst;
    private final long completeTime;
    private final long burstDelay;

    public BurstFireMode(int bulletsPerBurst, long completeTime) {
        this.bulletsPerBurst = bulletsPerBurst;
        this.completeTime = completeTime;
        this.burstDelay = (completeTime / bulletsPerBurst) / 50;
    }

    @Override
    public String identifier() {
        return "burst";
    }

    @Override
    public boolean executeGunShot(WarZGuns core, Player player, Gun gun) {
        UUID uuid = player.getUniqueId();
        if(core.getGunDelayMap().containsKey(uuid)) {
            if(System.currentTimeMillis() < core.getGunDelayMap().get(uuid)) {
                return false;
            }
        }
        core.getGunDelayMap().put(uuid, System.currentTimeMillis() + gun.getDelay());

        new BukkitRunnable() {
            @Override
            public void run() {
                int i = 0;
                if(i >= bulletsPerBurst) {
                    cancel();
                }
                Snowball bullet = player.launchProjectile(Snowball.class, player.getLocation().getDirection());
                bullet.setShooter(player);
                i++;
            }
        }.runTaskTimer(core.getPlugin(), 0L, burstDelay);
        return true;
    }

    public int getBulletsPerBurst() {
        return bulletsPerBurst;
    }

    public long getCompleteTime() {
        return completeTime;
    }
}

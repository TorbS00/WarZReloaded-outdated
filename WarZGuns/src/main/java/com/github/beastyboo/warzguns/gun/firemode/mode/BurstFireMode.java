package com.github.beastyboo.warzguns.gun.firemode.mode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import com.github.beastyboo.warzguns.gun.firemode.IFireMode;
import com.github.beastyboo.warzguns.gun.firemode.adapter.BurstFireAdapter;
import com.google.gson.annotations.JsonAdapter;
import org.bukkit.entity.Player;
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
            int i = 0;
            @Override
            public void run() {
                if(i >= bulletsPerBurst) {
                    cancel();
                    return;
                }
                core.getBulletFactory().createBullet(player, gun);
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

    @Override
    public String toString() {
        return "BurstFireMode{" +
                "bulletsPerBurst=" + bulletsPerBurst +
                ", completeTime=" + completeTime +
                ", burstDelay=" + burstDelay +
                '}';
    }
}

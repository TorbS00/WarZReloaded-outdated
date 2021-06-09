package com.github.beastyboo.warzguns.gun.firemode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SingleFireMode implements IFireMode {

    @Override
    public String identifier() {
        return "default";
    }

    @Override
    public boolean executeGunShot(WarZGuns core, Player player, Gun gun) {
        UUID uuid = player.getUniqueId();
        if(core.getGunDelayMap().containsKey(uuid)) {
            if(System.currentTimeMillis() < core.getGunDelayMap().get(uuid)) {
                return false;
            }
        }
        core.getBulletFactory().createBullet(player, gun);
        core.getGunDelayMap().put(uuid, System.currentTimeMillis() + gun.getDelay());
        return true;
    }

    @Override
    public String toString() {
        return "SingleFireMode{}";
    }
}

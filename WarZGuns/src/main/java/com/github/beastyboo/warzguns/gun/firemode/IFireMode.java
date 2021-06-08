package com.github.beastyboo.warzguns.gun.firemode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.entity.Player;

public interface IFireMode {

    String identifier();

    boolean executeGunShot(WarZGuns core, Player player, Gun gun);

}

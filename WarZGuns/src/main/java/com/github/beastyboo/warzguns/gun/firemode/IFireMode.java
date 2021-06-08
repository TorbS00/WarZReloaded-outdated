package com.github.beastyboo.warzguns.gun.firemode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.entity.Player;

public interface IFireMode {

    /**
     * Gets the String identity of a certain FireMode.
     * Used for IO purposes
     *
     * @return identity
     */
    String identifier();

    /**
     * Execute a gunshot from the players perspective.
     *
     * @param core plugin core
     * @param player targeted player
     * @param gun selected gun
     * @return true if gun was executed properly
     */
    boolean executeGunShot(WarZGuns core, Player player, Gun gun);

}

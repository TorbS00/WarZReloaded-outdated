package com.github.beastyboo.warzguns.gun.firemode;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class GunFireListener implements Listener {

    private final WarZGuns core;

    public GunFireListener(WarZGuns core) {
        this.core = core;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }

        if(event.getItem() == null) {
            return;
        }

        Gun gun = core.getGun(event.getItem().getType());

        if(gun == null) {
            return;
        }

        Player player = event.getPlayer();
        if(gun.getFireMode().executeGunShot(core, player, gun)) {
            //Recoil
        }
    }
}

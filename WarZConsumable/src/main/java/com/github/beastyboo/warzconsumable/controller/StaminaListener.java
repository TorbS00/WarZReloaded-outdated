package com.github.beastyboo.warzconsumable.controller;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import com.github.beastyboo.warzconsumable.entity.StaminaPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class StaminaListener implements Listener {

    private final WarZConsumable core;

    public StaminaListener(WarZConsumable core) {
        this.core = core;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        double highest = core.getConfig().staminaSettings().maxStaminaDefault();
        core.getStaminaPlayerMap().put(uuid, new StaminaPlayer(uuid, highest));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        StaminaPlayer player = core.getStaminaPlayerMap().get(uuid);

        if(player != null) {
            core.getStaminaPlayerMap().remove(uuid, player);
        }
    }
}

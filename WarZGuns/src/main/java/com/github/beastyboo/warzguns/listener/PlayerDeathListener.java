package com.github.beastyboo.warzguns.listener;

import com.github.beastyboo.warzguns.WarZGuns;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final WarZGuns core;

    public PlayerDeathListener(WarZGuns core) {
        this.core = core;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(player.getKiller() instanceof Snowball) {
            Snowball snowball = (Snowball) player.getKiller();
            Player shooter = (Player) snowball.getShooter();

            Bukkit.broadcastMessage(player.getName() + " killed by: " + shooter.getName());
        }
    }

}

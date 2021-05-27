package com.github.beastyboo.warzconsumable.controller;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ConsumableListener implements Listener {

    private final WarZConsumable core;

    public ConsumableListener(WarZConsumable core) {
        this.core = core;
    }

    @EventHandler
    public void onUseConsumable(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        IConsumableItem consumableItem = getConsumableItem(player);

        if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if(consumableItem == null) {
            return;
        }

        if(core.getConsumableDelay().containsKey(uuid)) {
            if(System.currentTimeMillis() < core.getConsumableDelay().get(player.getUniqueId())) {
                return;
            }
            core.getConsumableDelay().remove(player.getUniqueId());
        }

        if(consumableItem.onExecute(core, player)) {
            core.getConsumableDelay().put(uuid, System.currentTimeMillis() + consumableItem.delay(core));
            int amount = itemInMainHand.getAmount();
            if(amount > 1) {
                itemInMainHand.setAmount(amount - 1);
            } else {
                player.getInventory().remove(itemInMainHand);
            }
        }
    }

    private IConsumableItem getConsumableItem(Player player) {
        for(IConsumableItem consumableItem : core.getConsumableItem().values()) {
            if(player.getInventory().getItemInMainHand().isSimilar(consumableItem.item()))
                return consumableItem;
        }
        return null;
    }
}

package com.github.beastyboo.warzconsumable;

import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public interface WarZConsumableAPI {

    void reload();

    Map<UUID, Long> getConsumableDelay();

    Map<ItemStack, IConsumableItem> getConsumableItem();

}

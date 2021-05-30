package com.github.beastyboo.warzconsumable.api;

import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public interface WarZConsumableAPI {

    void reload();

    Map<UUID, Long> getConsumableDelayMapCopy();

    Map<ItemStack, IConsumableItem> getConsumableItemMapCopy();

}

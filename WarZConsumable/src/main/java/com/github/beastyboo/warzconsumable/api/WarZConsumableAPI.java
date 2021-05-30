package com.github.beastyboo.warzconsumable.api;

import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public interface WarZConsumableAPI {

    /**
     * Reloads the configuration for WarZConsumables. This will rarely be needed external developers, however
     * it is added, in case the developer depends on ensuring the soft code is modified.
     */
    void reload();

    /**
     *
     * Gets an immutable copy of the hashmap containing all consumable delays individually for
     * each player. For ensuring thread-safety this can't be modified from the external developer, however they
     * can get values from it if they ever need it. Such as checking if a player is currently in a cooldown.
     *
     * The IO of the hashmap is, and should only be handled internally by this plugin. Therefore it is only a copy.
     *
     * @return immutable copy of consumableDelay hashmap.
     */
    Map<UUID, Long> getConsumableDelayMapCopy();


    /**
     * Gets an immutable copy of the hashmap containing all consumable items. For ensuring thread-safety this can't be modified from the
     * external developer, however they can get values from it if they ever need it. Such as checking if an itemstack is similar to a consumable item.
     *
     * The IO of the hashmap is, and should only be handled internally by this plugin. Therefore it is only a copy.
     *
     * @return immutable copy of consumableItem hashmap.
     */
    Map<ItemStack, IConsumableItem> getConsumableItemMapCopy();

}

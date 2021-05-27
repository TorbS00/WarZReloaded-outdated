package com.github.beastyboo.warzconsumable.port;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IConsumableItem {

    ItemStack item();

    long delay(WarZConsumable warZConsumable);

    boolean onExecute(WarZConsumable warZConsumable, Player player);

}

package com.github.beastyboo.warzconsumable.entity;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CannedBeans implements IConsumableItem {

    @Override
    public ItemStack item() {
        return new ItemStack(Material.LAPIS_LAZULI);
    }

    @Override
    public long delay(WarZConsumable warZConsumable) {
        return warZConsumable.getConfig().cannedBeansDelay();
    }

    @Override
    public boolean onExecute(WarZConsumable warZConsumable, Player player) {
        UUID uuid = player.getUniqueId();
        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if(!playerItem.isSimilar(item())) {
            return false;
        }

        double health = warZConsumable.getConfig().cannedBeansHeal();
        double newHealth = player.getHealth() + health;
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if(newHealth >= maxHealth) {
            player.setHealth(maxHealth);
        } else {
            player.setHealth(newHealth);
        }

        return true;
    }
}

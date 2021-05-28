package com.github.beastyboo.warzconsumable.entity;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CannedPasta implements IConsumableItem {

    @Override
    public ItemStack item() {
        return new ItemStack(Material.INK_SAC, 1, (byte) 11);
    }

    @Override
    public long delay(WarZConsumable warZConsumable) {
        return warZConsumable.getConfig().foodSettings().cannedPastaDelay();
    }

    @Override
    public boolean onExecute(WarZConsumable warZConsumable, Player player) {
        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if(!playerItem.isSimilar(item())) {
            return false;
        }

        double health = warZConsumable.getConfig().foodSettings().cannedPastaHeal();
        double newHealth = player.getHealth() + health;
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        player.setHealth(Math.min(newHealth, maxHealth));

        int foodLevel = warZConsumable.getConfig().foodSettings().cannedPastaFood();
        int newFoodLevel = player.getFoodLevel() + foodLevel;
        int maxFoodLevel = 20;

        player.setFoodLevel(Math.min(newFoodLevel, maxFoodLevel));

        return true;
    }

}

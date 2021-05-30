package com.github.beastyboo.warzconsumable.entity.consumable;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import com.github.beastyboo.warzconsumable.entity.StaminaPlayer;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WaterBottle implements IConsumableItem {

    @Override
    public ItemStack item() {
        return new ItemStack(Material.POTION, 1, (byte) 0);
    }

    @Override
    public long delay(WarZConsumable warZConsumable) {
        return warZConsumable.getConfig().drinkSettings().waterbottleDelay();
    }

    @Override
    public boolean onExecute(WarZConsumable warZConsumable, Player player) {
        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if(!playerItem.isSimilar(item())) {
            return false;
        }

        StaminaPlayer staminaPlayer = warZConsumable.getStaminaPlayerMap().get(player.getUniqueId());
        if(staminaPlayer == null) {
            return false;
        }

        double health = warZConsumable.getConfig().drinkSettings().waterbottleHeal();
        double newHealth = player.getHealth() + health;
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        player.setHealth(Math.min(newHealth, maxHealth));

        int foodLevel = warZConsumable.getConfig().drinkSettings().waterbottleFood();
        int newFoodLevel = player.getFoodLevel() + foodLevel;
        int maxFoodLevel = 20;

        player.setFoodLevel(Math.min(newFoodLevel, maxFoodLevel));

        double newStamina = staminaPlayer.getCurrentStaminaLevel() + warZConsumable.getConfig().drinkSettings().waterbottleStamina();
        staminaPlayer.setCurrentStaminaLevel(Math.min(newStamina, staminaPlayer.getMaxStaminaLevel()));

        return true;
    }
}

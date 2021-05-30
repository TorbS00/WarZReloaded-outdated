package com.github.beastyboo.warzconsumable.entity.consumable;

import com.github.beastyboo.warzconsumable.WarZConsumable;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Sugar implements IConsumableItem {

    @Override
    public ItemStack item() {
        return new ItemStack(Material.SUGAR);
    }

    @Override
    public long delay(WarZConsumable warZConsumable) {
        return warZConsumable.getConfig().miscSettings().sugarDelay();
    }

    @Override
    public boolean onExecute(WarZConsumable warZConsumable, Player player) {
        ItemStack playerItem = player.getInventory().getItemInMainHand();

        if(!playerItem.isSimilar(item())) {
            return false;
        }

        if(player.hasPotionEffect(PotionEffectType.SPEED)) {
            return false;
        }

        int duration = warZConsumable.getConfig().miscSettings().sugarDuration() * 20;
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 1, true, true, true));
        return true;
    }
}

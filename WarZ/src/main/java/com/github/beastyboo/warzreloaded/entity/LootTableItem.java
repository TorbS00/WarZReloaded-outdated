package com.github.beastyboo.warzreloaded.entity;

import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class LootTableItem {

    private final ItemStack itemStack;
    private final double percentage;

    public LootTableItem(ItemStack itemStack, double percentage) {
        this.itemStack = itemStack;
        this.percentage = percentage;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LootTableItem that = (LootTableItem) o;
        return Double.compare(that.percentage, percentage) == 0 && itemStack.equals(that.itemStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemStack, percentage);
    }

    @Override
    public String toString() {
        return "LootTableItem{" +
                "itemStack=" + itemStack +
                ", percentage=" + percentage +
                '}';
    }
}

package com.github.beastyboo.warzguns.gun.ammo;

import com.github.beastyboo.warzguns.gun.WeaponClass;
import com.google.gson.Gson;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Ammo {

    private final String name;
    private final ItemStack itemStack;
    private final WeaponClass weaponClass;

    public Ammo(String name) {
        this.name = name;
        this.itemStack = new ItemStack(Material.POTION);
        this.weaponClass = WeaponClass.PISTOL;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public WeaponClass getWeaponClass() {
        return weaponClass;
    }

    @Override
    public String toString() {
        return "Ammo{" +
                "name='" + name + '\'' +
                '}';
    }

    public ItemStack fromGson(String string) {
        return new Gson().fromJson(string, ItemStack.class);
    }

    public String toGson(ItemStack itemStack) {
        return new Gson().toJson(itemStack);
    }

}

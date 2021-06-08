package com.github.beastyboo.warzguns.gun;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class GunTracker {

    private final Map<Material, Gun> gunMaterialMap;
    private final Map<String, Gun> gunNameMap;

    public GunTracker() {
        this.gunMaterialMap = new HashMap<>();
        this.gunNameMap = new HashMap<>();
    }

    /**
     * Adds a gun to both hashmaps for caching purposes.
     *
     * @param gun gun
     */
    public void addGun(Gun gun) {
        gunMaterialMap.put(gun.getMaterial(), gun);
        gunNameMap.put(gun.getGunName().toLowerCase(), gun);
    }

    /**
     * Gets a gun by checking both hashmaps for they key.
     * return null if there is no key match in neither of the maps.
     *
     * @param material gun material
     * @param name gun name
     * @return gun
     */
    public Gun getGun(Material material, String name) {
        if(material != null) {
            return getGun(material);
        }
        if(name != null) {
            return getGun(name);
        }
        return null;
    }

    /**
     * Gets a gun by checking it's material.
     * return null if no key match found
     *
     * @param material gun material
     * @return gun
     */
    public Gun getGun(Material material) {
        return gunMaterialMap.get(material);
    }

    /**
     * Gets a gun by checking it's name.
     * return null if no key match found
     *
     * @param name gun name
     * @return gun
     */
    public Gun getGun(String name) {
        return gunNameMap.get(name.toLowerCase());
    }

}

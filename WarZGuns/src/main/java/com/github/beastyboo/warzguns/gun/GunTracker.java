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

    public void addGun(Gun gun) {
        gunMaterialMap.put(gun.getMaterial(), gun);
        gunNameMap.put(gun.getGunName().toLowerCase(), gun);
    }

    public Gun getGun(Material material, String name) {
        if(material != null) {
            return getGun(material);
        }
        if(name != null) {
            return getGun(name);
        }
        return null;
    }

    public Gun getGun(Material material) {
        return gunMaterialMap.get(material);
    }

    public Gun getGun(String name) {
        return gunNameMap.get(name.toLowerCase());
    }

}

package com.github.beastyboo.warzguns.api;

import com.github.beastyboo.warzguns.gun.Gun;
import org.bukkit.Material;

public interface WarZGunsAPI {

    Gun getGun(Material material);

    Gun getGun(String name);

}

package com.github.beastyboo.warzguns.api;

import com.github.beastyboo.warzguns.calculator.AccuracyCalculator;
import com.github.beastyboo.warzguns.calculator.DamageCalculator;
import com.github.beastyboo.warzguns.gun.Gun;
import com.github.beastyboo.warzguns.gun.bullet.Bullet;
import org.bukkit.Material;

import java.util.UUID;

public interface WarZGunsAPI {

    Gun getGun(Material material);

    Gun getGun(String name);

    Bullet getBullet(UUID uuid);

    AccuracyCalculator getAccuracyCalculator();

    DamageCalculator getDamageCalculator();

}

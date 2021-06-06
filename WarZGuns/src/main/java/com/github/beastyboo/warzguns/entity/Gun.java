package com.github.beastyboo.warzguns.entity;

import com.github.beastyboo.warzguns.gun.FireMode;
import com.github.beastyboo.warzguns.gun.WeaponClass;
import org.bukkit.Material;

public interface Gun {

    String name();

    WeaponClass weaponClass();

    Material material();

    Ammo ammo();

    FireMode fireMode();

    boolean canAim();

    long reloadTime();

    long delay();

    int maxDistance();

    int maxClipSize();

    double damagePerBullet();

    double bulletSpeed();

    double accuracy();

    double accuracyAimed();

    double accuracyCrouched();

    double targetKnockBack();

    double recoil();

}

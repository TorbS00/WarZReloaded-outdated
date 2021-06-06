package com.github.beastyboo.warzguns.entity;


import com.github.beastyboo.warzguns.gun.FireMode;
import com.github.beastyboo.warzguns.gun.WeaponClass;
import org.bukkit.Material;

public class Pistol implements Gun {

    private final String gunName;
    private final Material material;
    private final Ammo ammo;
    private final FireMode fireMode;

    private final boolean canAim;

    private final long reloadTime;
    private final long delay;

    private final int maxDistance;
    private final int maxClipSize;

    private final double damagePerBullet;
    private final double bulletSpeed;
    private final double accuracy;
    private final double accuracy_aimed;
    private final double accuracy_crouched;
    private final double targetKnockBack;
    private final double recoil;

    public Pistol(String gunName, Material material, Ammo ammo, FireMode fireMode, boolean canAim, long reloadTime, long delay, int maxDistance, int maxClipSize, double damagePerBullet, double bulletSpeed, double accuracy, double accuracy_aimed, double accuracy_crouched, double targetKnockBack, double recoil) {
        this.gunName = gunName;
        this.material = material;
        this.ammo = ammo;
        this.fireMode = fireMode;
        this.canAim = canAim;
        this.reloadTime = reloadTime;
        this.delay = delay;
        this.maxDistance = maxDistance;
        this.maxClipSize = maxClipSize;
        this.damagePerBullet = damagePerBullet;
        this.bulletSpeed = bulletSpeed;
        this.accuracy = accuracy;
        this.accuracy_aimed = accuracy_aimed;
        this.accuracy_crouched = accuracy_crouched;
        this.targetKnockBack = targetKnockBack;
        this.recoil = recoil;
    }

    @Override
    public String name() {
        return gunName;
    }

    @Override
    public WeaponClass weaponClass() {
        return WeaponClass.PISTOL;
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public Ammo ammo() {
        return ammo;
    }

    @Override
    public FireMode fireMode() {
        return fireMode;
    }

    @Override
    public boolean canAim() {
        return canAim;
    }

    @Override
    public long reloadTime() {
        return reloadTime;
    }

    @Override
    public long delay() {
        return delay;
    }

    @Override
    public int maxDistance() {
        return maxDistance;
    }

    @Override
    public int maxClipSize() {
        return maxClipSize;
    }

    @Override
    public double damagePerBullet() {
        return damagePerBullet;
    }

    @Override
    public double bulletSpeed() {
        return bulletSpeed;
    }

    @Override
    public double accuracy() {
        return accuracy;
    }

    @Override
    public double accuracyAimed() {
        return accuracy_aimed;
    }

    @Override
    public double accuracyCrouched() {
        return accuracy_crouched;
    }

    @Override
    public double targetKnockBack() {
        return targetKnockBack;
    }

    @Override
    public double recoil() {
        return recoil;
    }
}

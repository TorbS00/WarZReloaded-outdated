package com.github.beastyboo.warzguns;

import com.github.beastyboo.warzguns.gun.Ammo;
import org.bukkit.Material;

public class Gun {

    private final String gunName;
    private final Material material;
    private final boolean canAim;
    private final Ammo ammo;
    private final double damage;
    private final int roundsPerBurst;
    private final long reloadTime;
    private final int maxDistance;
    private final long delay;
    private final double bulletSpeed;
    private final double accuracy;
    private final double accuracy_aimed;
    private final double accuracy_crouched;
    private final double targetKnockBack;
    private final double recoil;
    private final int maxClipSize;

    public Gun(String gunName, Material material, boolean canAim, Ammo ammo, double damage, int roundsPerBurst, long reloadTime, int maxDistance, long delay, double bulletSpeed, double accuracy, double accuracy_aimed, double accuracy_crouched, double targetKnockback, double recoil, int maxClipSize) {
        this.gunName = gunName;
        this.material = material;
        this.canAim = canAim;
        this.ammo = ammo;
        this.damage = damage;
        this.roundsPerBurst = roundsPerBurst;
        this.reloadTime = reloadTime;
        this.maxDistance = maxDistance;
        this.delay = delay;
        this.bulletSpeed = bulletSpeed;
        this.accuracy = accuracy;
        this.accuracy_aimed = accuracy_aimed;
        this.accuracy_crouched = accuracy_crouched;
        this.targetKnockBack = targetKnockback;
        this.recoil = recoil;
        this.maxClipSize = maxClipSize;
    }

    public String getGunName() {
        return gunName;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isCanAim() {
        return canAim;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public double getDamage() {
        return damage;
    }

    public int getRoundsPerBurst() {
        return roundsPerBurst;
    }

    public long getReloadTime() {
        return reloadTime;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public long getDelay() {
        return delay;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getAccuracy_aimed() {
        return accuracy_aimed;
    }

    public double getAccuracy_crouched() {
        return accuracy_crouched;
    }

    public double getTargetKnockBack() {
        return targetKnockBack;
    }

    public double getRecoil() {
        return recoil;
    }

    public int getMaxClipSize() {
        return maxClipSize;
    }
}

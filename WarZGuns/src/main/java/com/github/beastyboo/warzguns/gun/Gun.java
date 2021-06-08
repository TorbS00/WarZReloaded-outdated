package com.github.beastyboo.warzguns.gun;

import com.github.beastyboo.warzguns.gun.firemode.FireModeType;
import com.github.beastyboo.warzguns.gun.firemode.IFireMode;
import com.google.gson.annotations.JsonAdapter;
import org.bukkit.Material;

import java.util.Objects;

@JsonAdapter(GunAdapter.class)
public class Gun {

    private final String gunName;
    private final Material material;
    private final Ammo ammo;
    private final WeaponClass weaponClass;
    private final FireModeType fireModeType;
    private final IFireMode fireMode;

    private final boolean canAim;

    private final long reloadTime;
    private final long delay;

    private final int maxDistance;
    private final int maxClipSize;

    private final double damagePerBullet;
    private final double headShotIncrease;
    private final double legShotDecrease;
    private final double bulletSpeed;
    private final double accuracy;
    private final double accuracy_aimed;
    private final double accuracy_crouched;
    private final double targetKnockBack;
    private final double recoil;

    public Gun(String gunName, Material material, Ammo ammo, WeaponClass weaponClass, FireModeType fireModeType, IFireMode fireMode, boolean canAim, long reloadTime, long delay, int maxDistance, int maxClipSize, double damagePerBullet, double headShotIncrease, double legShotDecrease, double bulletSpeed, double accuracy, double accuracy_aimed, double accuracy_crouched, double targetKnockBack, double recoil) {
        this.gunName = gunName;
        this.material = material;
        this.ammo = ammo;
        this.weaponClass = weaponClass;
        this.fireModeType = fireModeType;
        this.fireMode = fireMode;
        this.canAim = canAim;
        this.reloadTime = reloadTime;
        this.delay = delay;
        this.maxDistance = maxDistance;
        this.maxClipSize = maxClipSize;
        this.damagePerBullet = damagePerBullet;
        this.headShotIncrease = headShotIncrease;
        this.legShotDecrease = legShotDecrease;
        this.bulletSpeed = bulletSpeed;
        this.accuracy = accuracy;
        this.accuracy_aimed = accuracy_aimed;
        this.accuracy_crouched = accuracy_crouched;
        this.targetKnockBack = targetKnockBack;
        this.recoil = recoil;
    }

    public Gun(String gunName, Material material, WeaponClass weaponClass, FireModeType fireModeType, IFireMode fireMode) {
        this.gunName = gunName;
        this.material = material;
        this.ammo = new Ammo("Cool-ammo");
        this.weaponClass = weaponClass;
        this.fireModeType = fireModeType;
        this.fireMode = fireMode;
        this.canAim = true;
        this.reloadTime = 5;
        this.delay = 5;
        this.maxDistance = 5;
        this.maxClipSize = 5;
        this.damagePerBullet = 5;
        this.headShotIncrease = 5;
        this.legShotDecrease = 5;
        this.bulletSpeed = 5;
        this.accuracy = 5;
        this.accuracy_aimed = 5;
        this.accuracy_crouched = 5;
        this.targetKnockBack = 5;
        this.recoil = 5;
    }

    public String getGunName() {
        return gunName;
    }

    public Material getMaterial() {
        return material;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public WeaponClass getWeaponClass() {
        return weaponClass;
    }

    public FireModeType getFireModeType() {
        return fireModeType;
    }

    public IFireMode getFireMode() {
        return fireMode;
    }

    public boolean isCanAim() {
        return canAim;
    }

    public long getReloadTime() {
        return reloadTime;
    }

    public long getDelay() {
        return delay;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxClipSize() {
        return maxClipSize;
    }

    public double getDamagePerBullet() {
        return damagePerBullet;
    }

    public double getHeadShotIncrease() {
        return headShotIncrease;
    }

    public double getLegShotDecrease() {
        return legShotDecrease;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gun gun = (Gun) o;
        return canAim == gun.canAim && reloadTime == gun.reloadTime && delay == gun.delay && maxDistance == gun.maxDistance && maxClipSize == gun.maxClipSize && Double.compare(gun.damagePerBullet, damagePerBullet) == 0 && Double.compare(gun.headShotIncrease, headShotIncrease) == 0 && Double.compare(gun.legShotDecrease, legShotDecrease) == 0 && Double.compare(gun.bulletSpeed, bulletSpeed) == 0 && Double.compare(gun.accuracy, accuracy) == 0 && Double.compare(gun.accuracy_aimed, accuracy_aimed) == 0 && Double.compare(gun.accuracy_crouched, accuracy_crouched) == 0 && Double.compare(gun.targetKnockBack, targetKnockBack) == 0 && Double.compare(gun.recoil, recoil) == 0 && gunName.equals(gun.gunName) && material == gun.material && ammo.equals(gun.ammo) && weaponClass == gun.weaponClass && fireModeType == gun.fireModeType && fireMode.equals(gun.fireMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gunName, material, ammo, weaponClass, fireModeType, fireMode, canAim, reloadTime, delay, maxDistance, maxClipSize, damagePerBullet, headShotIncrease, legShotDecrease, bulletSpeed, accuracy, accuracy_aimed, accuracy_crouched, targetKnockBack, recoil);
    }

    @Override
    public String toString() {
        return "Gun{" +
                "gunName='" + gunName + '\'' +
                ", material=" + material +
                ", ammo=" + ammo +
                ", weaponClass=" + weaponClass +
                ", fireModeType=" + fireModeType +
                ", fireMode=" + fireMode +
                ", canAim=" + canAim +
                ", reloadTime=" + reloadTime +
                ", delay=" + delay +
                ", maxDistance=" + maxDistance +
                ", maxClipSize=" + maxClipSize +
                ", damagePerBullet=" + damagePerBullet +
                ", headShotIncrease=" + headShotIncrease +
                ", legShotDecrease=" + legShotDecrease +
                ", bulletSpeed=" + bulletSpeed +
                ", accuracy=" + accuracy +
                ", accuracy_aimed=" + accuracy_aimed +
                ", accuracy_crouched=" + accuracy_crouched +
                ", targetKnockBack=" + targetKnockBack +
                ", recoil=" + recoil +
                '}';
    }
}

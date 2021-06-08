package com.github.beastyboo.warzguns.gun;

import com.github.beastyboo.warzguns.gun.firemode.*;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Material;

import java.io.IOException;

public class GunAdapter extends TypeAdapter<Gun> {

    @Override
    public void write(JsonWriter out, Gun gun) throws IOException {
        out.beginObject();
        out.name("_id").value(gun.getGunName());
        out.name("material").value(gun.getMaterial().toString());
        out.name("ammo").value(gun.getAmmo().getName());
        out.name("weaponClass").value(gun.getWeaponClass().name());
        out.name("fireModeType").value(gun.getFireModeType().name());
        out.name("fireMode-identifier").value(gun.getFireMode().identifier());

        if(gun.getFireMode() instanceof SingleFireMode) {
            out.name("fireMode").value("single fire mode");
        }

        else if(gun.getFireMode() instanceof ShotgunFireMode) {
            ShotgunFireMode fireMode = (ShotgunFireMode) gun.getFireMode();
            TypeAdapter<ShotgunFireMode> shotgunAdapter = new Gson().getAdapter(ShotgunFireMode.class);
            out.name("fireMode");
            shotgunAdapter.write(out, fireMode);
        }

        else if(gun.getFireMode() instanceof BurstFireMode) {
            BurstFireMode fireMode = (BurstFireMode) gun.getFireMode();
            TypeAdapter<BurstFireMode> burstAdapter = new Gson().getAdapter(BurstFireMode.class);
            out.name("fireMode");
            burstAdapter.write(out, fireMode);
        }

        out.name("canAim").value(gun.isCanAim());

        out.name("reloadTime").value(gun.getReloadTime());
        out.name("delay").value(gun.getDelay());

        out.name("maxDistance").value(gun.getMaxDistance());
        out.name("maxClipSize").value(gun.getMaxClipSize());

        out.name("damagePerBullet").value(gun.getDamagePerBullet());
        out.name("headShotIncrease").value(gun.getHeadShotIncrease());
        out.name("legShotDecrease").value(gun.getLegShotDecrease());
        out.name("bulletSpeed").value(gun.getBulletSpeed());
        out.name("accuracy").value(gun.getAccuracy());
        out.name("accuracy_aimed").value(gun.getAccuracy_aimed());
        out.name("accuracy_crouched").value(gun.getAccuracy_crouched());
        out.name("targetKnockBack").value(gun.getTargetKnockBack());
        out.name("recoil").value(gun.getRecoil());

        out.endObject();
    }

    @Override
    public Gun read(JsonReader in) throws IOException {
        in.beginObject();

        String gunName = "";
        Material material = null;
        Ammo ammo = null;
        WeaponClass weaponClass = null;
        FireModeType fireModeType = null;

        String fireModeIdentifier = "";
        IFireMode fireMode = null;

        boolean canAim = false;

        long reloadTime = 0;
        long delay = 0;

        int maxDistance = 0;
        int maxClipSize = 0;

        double damagePerBullet = 0;
        double headShotIncrease = 0;
        double legShotDecrease = 0;
        double bulletSpeed = 0;
        double accuracy = 0;
        double accuracy_aimed = 0;
        double accuracy_crouched = 0;
        double targetKnockBack = 0;
        double recoil = 0;

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "_id":
                    gunName = in.nextString();
                    break;
                case "material":
                    material = Material.getMaterial(in.nextString());
                    break;
                case "ammo":
                    ammo = new Ammo(in.nextString());
                    break;
                case "weaponClass":
                    weaponClass = Enum.valueOf(WeaponClass.class, in.nextString());
                    break;
                case "fireModeType":
                    fireModeType = Enum.valueOf(FireModeType.class, in.nextString());
                    break;
                case "fireMode-identifier":
                     fireModeIdentifier = in.nextString();
                    break;
                case "fireMode":
                    if(fireModeIdentifier.equalsIgnoreCase("default")) {
                        String test = in.nextString();
                        fireMode = new SingleFireMode();
                    }
                    else if(fireModeIdentifier.equalsIgnoreCase("shotgun")) {
                        TypeAdapter<ShotgunFireMode> shotgunAdapter = new Gson().getAdapter(ShotgunFireMode.class);
                        fireMode = shotgunAdapter.read(in);
                    }
                    else if(fireModeIdentifier.equalsIgnoreCase("burst")) {
                        TypeAdapter<BurstFireMode> burstAdapter = new Gson().getAdapter(BurstFireMode.class);
                        fireMode = burstAdapter.read(in);
                    }
                    break;
                case "canAim":
                    canAim = in.nextBoolean();
                    break;
                case "reloadTime":
                    reloadTime = in.nextLong();
                    break;
                case "delay":
                    delay = in.nextLong();
                    break;
                case "maxDistance":
                    maxDistance = in.nextInt();
                    break;
                case "maxClipSize":
                    maxClipSize = in.nextInt();
                    break;
                case "damagePerBullet":
                    damagePerBullet = in.nextDouble();
                    break;
                case "headShotIncrease":
                    headShotIncrease = in.nextDouble();
                    break;
                case "legShotDecrease":
                    legShotDecrease = in.nextDouble();
                    break;
                case "bulletSpeed":
                    bulletSpeed = in.nextDouble();
                    break;
                case "accuracy":
                    accuracy = in.nextDouble();
                    break;
                case "accuracy_aimed":
                    accuracy_aimed = in.nextDouble();
                    break;
                case "accuracy_crouched":
                    accuracy_crouched = in.nextDouble();
                    break;
                case "targetKnockBack":
                    targetKnockBack = in.nextDouble();
                    break;
                case "recoil":
                    recoil = in.nextDouble();
                    break;
                default:
                    throw new IOException("Gun files are corrupt! Appeared in: " + gunName);
            }
        }
        Gun gun = new Gun(gunName, material, ammo, weaponClass, fireModeType, fireMode, canAim, reloadTime, delay, maxDistance, maxClipSize, damagePerBullet,
                headShotIncrease, legShotDecrease, bulletSpeed, accuracy, accuracy_aimed, accuracy_crouched, targetKnockBack, recoil);
        in.endObject();
        return gun;
    }
}

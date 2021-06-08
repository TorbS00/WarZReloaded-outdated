package com.github.beastyboo.warzguns.gun;

import com.github.beastyboo.warzguns.WarZGuns;
import com.github.beastyboo.warzguns.gun.firemode.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Material;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class GunFactory {

    private final WarZGuns core;
    private final GunTracker gunTracker;
    private final Gson gson;
    private final File gunFolder;

    private volatile Set<Gun> testGuns = new HashSet<>();

    public GunFactory(WarZGuns core) {
        this.core = core;
        this.gunTracker = new GunTracker();
        this.gson = initializeGson();
        this.gunFolder = new File(core.getPlugin().getDataFolder(), "guns");
    }

    public void executeGunFactory() {

        if(!gunFolder.exists()) {
            gunFolder.mkdirs();
        }

        File[] levelListing = gunFolder.listFiles();
        if (levelListing == null) {
            return;
        }

        for (File child : levelListing) {
            String json = this.loadContent(child);
            Gun gun = this.deserialize(json);
            gunTracker.addGun(gun);
        }

    }

    public void createTestGuns() {
        //Single-shot:
        IFireMode singleShot = new SingleFireMode();
        Gun barret = new Gun("Barret", Material.DIAMOND_AXE, WeaponClass.SNIPER_RIFLE, FireModeType.BOLT, singleShot);

        //Shotgun
        IFireMode shotgunShot = new ShotgunFireMode(5);
        Gun spas = new Gun("spas", Material.DIAMOND_PICKAXE, WeaponClass.SHOTGUN, FireModeType.PUMP, shotgunShot);

        //Burst
        IFireMode burstShot = new BurstFireMode(3, 1000);
        Gun m16 = new Gun("m16", Material.IRON_HOE, WeaponClass.ASSAULT_RIFLE, FireModeType.BURST, burstShot);

        testGuns.add(barret);
        testGuns.add(spas);
        testGuns.add(m16);

        core.getExecutor().execute(() -> {
            if(!gunFolder.exists()) {
                gunFolder.mkdirs();
            }

            this.saveFile(new File(gunFolder, barret.getGunName() + ".json"), this.serialize(barret));
            this.saveFile(new File(gunFolder, spas.getGunName() + ".json"), this.serialize(spas));
            this.saveFile(new File(gunFolder, m16.getGunName() + ".json"), this.serialize(m16));
        });

        /*
        for(Gun gun : testGuns) {
            File file = new File(gunFolder, gun.getGunName() + ".json");
            if(!gunFolder.exists()) {
                gunFolder.mkdirs();
            }
            String json = this.serialize(gun);
            this.saveFile(file, json);
        }
         */

    }

    public GunTracker getGunTracker() {
        return gunTracker;
    }

    private void saveFile(File file, String json) {
        final FileWriter fw;
        try {
            file.createNewFile();
            fw = new FileWriter(file);
            fw.write(json);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);

        }
    }

    private String loadContent(File file) {
        if(file.exists()) {
            try {
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                final  StringBuilder text = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }
                reader.close();
                return text.toString();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
        return "";
    }

    private String serialize(Gun value) {
        return this.gson.toJson(value);
    }

    private Gun deserialize(String json) {
        return this.gson.fromJson(json, Gun.class);
    }

    private Gson initializeGson() {
        return new GsonBuilder().registerTypeAdapter(Gun.class, new GunAdapter())
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

}

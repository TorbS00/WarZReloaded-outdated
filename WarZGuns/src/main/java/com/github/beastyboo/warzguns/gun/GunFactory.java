package com.github.beastyboo.warzguns.gun;

import com.github.beastyboo.warzguns.WarZGuns;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class GunFactory {

    private final WarZGuns core;
    private final GunTracker gunTracker;
    private final Gson gson;
    private final File gunFolder;

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

package com.github.beastyboo.warzguns.gun.firemode.adapter;

import com.github.beastyboo.warzguns.gun.firemode.BurstFireMode;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BurstFireAdapter extends TypeAdapter<BurstFireMode> {

    @Override
    public void write(JsonWriter out, BurstFireMode burst) throws IOException {
        out.beginObject();
        out.name("bulletsPerBurst").value(burst.getBulletsPerBurst());
        out.name("completeTime").value(burst.getCompleteTime());
        out.endObject();
    }

    @Override
    public BurstFireMode read(JsonReader in) throws IOException {
        in.beginObject();
        int bulletsPerBurst = 0;
        long completeTime = 0;

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "bulletsPerBurst":
                    bulletsPerBurst = in.nextInt();
                    break;
                case "completeTime":
                    completeTime = in.nextLong();
                    break;
                default:
                    throw new IOException("Gun files are corrupt! Appeared in: burst-fire section.");
            }
        }

        in.endObject();
        return new BurstFireMode(bulletsPerBurst, completeTime);
    }
}

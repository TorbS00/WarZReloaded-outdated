package com.github.beastyboo.warzguns.gun.firemode.adapter;

import com.github.beastyboo.warzguns.gun.firemode.ShotgunFireMode;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ShotgunFireAdapter extends TypeAdapter<ShotgunFireMode> {

    @Override
    public void write(JsonWriter out, ShotgunFireMode shotgun) throws IOException {
        out.beginObject();
        out.name("bulletsPerRound").value(shotgun.getBulletsPerRound());
        out.endObject();
    }

    @Override
    public ShotgunFireMode read(JsonReader in) throws IOException {
        in.beginObject();
        int bulletsPerRound = 0;

        while (in.hasNext()) {
            switch (in.nextName()) {
                case "bulletsPerRound":
                    bulletsPerRound = in.nextInt();
                    break;
                default:
                    in.skipValue();
            }
        }

        in.endObject();
        return new ShotgunFireMode(bulletsPerRound);
    }
}

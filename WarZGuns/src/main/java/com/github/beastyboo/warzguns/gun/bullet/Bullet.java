package com.github.beastyboo.warzguns.gun.bullet;

import com.github.beastyboo.warzguns.gun.Gun;

import java.util.Objects;
import java.util.UUID;

public class Bullet {

    private final UUID uuid;
    private final Gun gun;

    public Bullet(UUID uuid, Gun gun) {
        this.uuid = uuid;
        this.gun = gun;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bullet bullet = (Bullet) o;
        return uuid.equals(bullet.uuid) && gun.equals(bullet.gun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, gun);
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "uuid=" + uuid +
                ", gun=" + gun +
                '}';
    }
}

package com.github.beastyboo.warzguns.gun.bullet;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BulletTracker {

    /**
     * Using google.cache library to ensure a bullet's max lifetime is 10 seconds before
     * the garbage collector remove it.
     */
    private final Cache<UUID, Bullet> bulletCache;

    public BulletTracker() {
        bulletCache = CacheBuilder.newBuilder().expireAfterWrite(10L, TimeUnit.SECONDS).build();
    }

    protected void addBullet(Bullet bullet) {
        bulletCache.put(bullet.getUuid(), bullet);
    }

    public Bullet getBullet(UUID uuid) {
        return bulletCache.getIfPresent(uuid);
    }

}

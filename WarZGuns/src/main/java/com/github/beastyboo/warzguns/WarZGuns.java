package com.github.beastyboo.warzguns;

import com.github.beastyboo.warzguns.api.WarZGunsAPI;
import com.github.beastyboo.warzguns.calculator.AccuracyCalculator;
import com.github.beastyboo.warzguns.calculator.DamageCalculator;
import com.github.beastyboo.warzguns.gun.Gun;
import com.github.beastyboo.warzguns.gun.GunFactory;
import com.github.beastyboo.warzguns.gun.bullet.Bullet;
import com.github.beastyboo.warzguns.gun.bullet.BulletFactory;
import com.github.beastyboo.warzguns.gun.bullet.BulletHitListener;
import com.github.beastyboo.warzguns.gun.firemode.GunFireListener;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class WarZGuns implements WarZGunsAPI {

    final JavaPlugin plugin;
    private final Logger logger;
    private final Executor executor;
    private final Map<UUID, Long> gunDelayMap;

    private GunFactory gunFactory;
    private BulletFactory bulletFactory;

    private DamageCalculator damageCalculator;
    private AccuracyCalculator accuracyCalculator;

    WarZGuns(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        gunDelayMap = new HashMap<>();
        executor = Executors.newFixedThreadPool(4);
    }

    void load() {
        gunFactory = new GunFactory(this);
        bulletFactory = new BulletFactory(this);
        damageCalculator = new DamageCalculator(this);
        accuracyCalculator = new AccuracyCalculator();

        plugin.getServer().getPluginManager().registerEvents(new GunFireListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BulletHitListener(this), plugin);

        gunFactory.executeGunFactory();
    }

    void close() {

    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public Map<UUID, Long> getGunDelayMap() {
        return gunDelayMap;
    }

    /**
     *  Since the plugin will be be executing a lot of IO and other heavy tasks,
     *  we create our own thread pool in order to reduce thread creation delay.
     *
     *  DO NOT USE THIS FOR FEATURES INVOLVING THE BUKKIT API.
     *
     * @return Executor (Thread pool)
     */
    public Executor getExecutor() {
        return executor;
    }

    public GunFactory getGunFactory() {
        return gunFactory;
    }

    public BulletFactory getBulletFactory() {
        return bulletFactory;
    }

    @Override
    public DamageCalculator getDamageCalculator() {
        return damageCalculator;
    }

    @Override
    public AccuracyCalculator getAccuracyCalculator() {
        return accuracyCalculator;
    }

    @Override
    public Gun getGun(Material material) {
        return gunFactory.getGunTracker().getGun(material);
    }

    @Override
    public Gun getGun(String name) {
        return gunFactory.getGunTracker().getGun(name);
    }

    @Override
    public Bullet getBullet(UUID uuid) {
        return bulletFactory.getBulletTracker().getBullet(uuid);
    }
}

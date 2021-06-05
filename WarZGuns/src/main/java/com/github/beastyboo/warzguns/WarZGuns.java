package com.github.beastyboo.warzguns;

import com.github.beastyboo.warzguns.calculator.DamageCalculator;
import com.github.beastyboo.warzguns.listener.TestEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class WarZGuns {

    final JavaPlugin plugin;
    private final Logger logger;
    private final Executor executor;

    private DamageCalculator damageCalculator;

    WarZGuns(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        executor = Executors.newFixedThreadPool(4);
    }

    void load() {
        damageCalculator = new DamageCalculator(this);

        plugin.getServer().getPluginManager().registerEvents(new TestEvents(this), plugin);
    }

    void close() {

    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
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

    public DamageCalculator getDamageCalculator() {
        return damageCalculator;
    }
}

package com.github.beastyboo.warzreloaded.application;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WarZ {

    final JavaPlugin plugin;
    private final Logger logger;

    WarZ(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
    }

    void load() {

    }

    void close() {

    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }
}

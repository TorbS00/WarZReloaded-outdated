package com.github.beastyboo.warzguns;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WarZGuns {

    final JavaPlugin plugin;
    private final Logger logger;

    WarZGuns(JavaPlugin plugin) {
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

package com.github.beastyboo.warzreloaded.application;

import org.bukkit.plugin.java.JavaPlugin;

public final class WarZPlugin extends JavaPlugin {

    private WarZ core;

    @Override
    public void onEnable() {
        core = new WarZ(this);
        core.load();
    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }
}

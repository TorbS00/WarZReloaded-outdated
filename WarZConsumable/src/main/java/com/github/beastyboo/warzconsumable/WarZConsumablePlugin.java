package com.github.beastyboo.warzconsumable;

import org.bukkit.plugin.java.JavaPlugin;

public final class WarZConsumablePlugin extends JavaPlugin {

    private WarZConsumable core;

    @Override
    public void onEnable() {
        if(getServer().getPluginManager().getPlugin("WarZ") == null) {
            getLogger().warning("[WarZ] Not found! Shutting down plugin.");
            getLogger().warning("WarZConsumable can't run individually.");
            getServer().getPluginManager().disablePlugin(this);
        }

        core = new WarZConsumable(this);
        core.load();
    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }
}

package com.github.beastyboo.warzconsumable;

import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarZConsumablePlugin extends JavaPlugin {

    private WarZConsumable core;

    @Override
    public void onEnable() {
        core = new WarZConsumable(this);
        core.load();
        getServer().getServicesManager().register(WarZConsumableAPI.class, core, this, ServicePriority.Low);
    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }
}

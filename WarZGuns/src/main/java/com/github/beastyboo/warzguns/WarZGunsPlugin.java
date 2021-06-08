package com.github.beastyboo.warzguns;

import com.github.beastyboo.warzguns.api.WarZGunsAPI;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarZGunsPlugin extends JavaPlugin {

    private WarZGuns core;

    @Override
    public void onEnable() {
        core = new WarZGuns(this);
        core.load();
        getServer().getServicesManager().register(WarZGunsAPI.class, core, this, ServicePriority.Low);
    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }
}

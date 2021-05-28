package com.github.beastyboo.warzguns;

import org.bukkit.plugin.java.JavaPlugin;

public final class WarZGunsPlugin extends JavaPlugin {

    private WarZGuns core;

    @Override
    public void onEnable() {
        core = new WarZGuns(this);
        core.load();

    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }
}

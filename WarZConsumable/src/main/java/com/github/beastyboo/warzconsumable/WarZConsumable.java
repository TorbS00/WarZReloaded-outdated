package com.github.beastyboo.warzconsumable;

import com.github.beastyboo.warzconsumable.config.YamlPortConfiguration;
import com.github.beastyboo.warzconsumable.entity.*;
import com.github.beastyboo.warzconsumable.port.IConfig;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class WarZConsumable implements WarZConsumableAPI{

    final JavaPlugin plugin;
    private final Logger logger;
    private final Map<UUID, Long> consumableDelay;
    private final Map<ItemStack, IConsumableItem> consumableItem;
    private final YamlPortConfiguration<IConfig> configManager;

    private IConfig config;

    WarZConsumable(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        consumableDelay = new HashMap<>();
        consumableItem = new HashMap<>();
        configManager = YamlPortConfiguration.create(plugin.getDataFolder().toPath(), "config.yml", IConfig.class);
        configManager.reloadConfig();
        config = configManager.getConfigData();
    }

    void load() {
        registerConsumableItems();
    }

    void close() {

    }

    private void registerConsumableItems() {
        IConsumableItem cannedBeans = new CannedBeans();
        consumableItem.put(cannedBeans.item(), cannedBeans);

        IConsumableItem cannedPasta = new CannedPasta();
        consumableItem.put(cannedPasta.item(), cannedPasta);

        IConsumableItem cornedBeef = new CornedBeef();
        consumableItem.put(cornedBeef.item(), cornedBeef);

        IConsumableItem sugar = new Sugar();
        consumableItem.put(sugar.item(), sugar);

        IConsumableItem goldenApple = new GoldenApple();
        consumableItem.put(goldenApple.item(), goldenApple);

    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void reload() {

    }

    @Override
    public Map<UUID, Long> getConsumableDelay() {
        return consumableDelay;
    }

    @Override
    public Map<ItemStack, IConsumableItem> getConsumableItem() {
        return consumableItem;
    }

    public IConfig getConfig() {
        return config;
    }
}

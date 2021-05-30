package com.github.beastyboo.warzconsumable;

import com.github.beastyboo.warzconsumable.api.WarZConsumableAPI;
import com.github.beastyboo.warzconsumable.config.YamlPortConfiguration;
import com.github.beastyboo.warzconsumable.controller.ConsumableListener;
import com.github.beastyboo.warzconsumable.controller.StaminaListener;
import com.github.beastyboo.warzconsumable.entity.StaminaPlayer;
import com.github.beastyboo.warzconsumable.entity.consumable.*;
import com.github.beastyboo.warzconsumable.port.IConfig;
import com.github.beastyboo.warzconsumable.port.IConsumableItem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.logging.Logger;

public class WarZConsumable implements WarZConsumableAPI {

    final JavaPlugin plugin;

    private final Logger logger;
    private final Map<UUID, Long> consumableDelay;
    private final Map<ItemStack, IConsumableItem> consumableItem;
    private final Map<UUID, StaminaPlayer> staminaPlayerMap;
    private final YamlPortConfiguration<IConfig> configManager;
    private final PotionEffect slownessPotion;
    private final PotionEffect jumpDenyPotion;

    private IConfig config;

    WarZConsumable(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        consumableDelay = new HashMap<>();
        consumableItem = new HashMap<>();
        staminaPlayerMap = new HashMap<>();

        configManager = YamlPortConfiguration.create(plugin.getDataFolder().toPath(), "config.yml", IConfig.class);
        configManager.reloadConfig();
        config = configManager.getConfigData();

        this.slownessPotion = new PotionEffect(PotionEffectType.SLOW, 2, 3, true, false);
        this.jumpDenyPotion = new PotionEffect(PotionEffectType.JUMP, 2, 250, true, false);
    }

    void load() {
        registerListener(plugin.getServer().getPluginManager());

        registerConsumableItems();
        updateStaminaPlayers();
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

        IConsumableItem mountainDew = new MountainDew();
        consumableItem.put(mountainDew.item(), mountainDew);

        IConsumableItem pepsi = new Pepsi();
        consumableItem.put(pepsi.item(), pepsi);

        IConsumableItem waterBottle = new WaterBottle();
        consumableItem.put(waterBottle.item(), waterBottle);
    }

    private void registerListener(PluginManager manager) {
        manager.registerEvents(new ConsumableListener(this), plugin);
        manager.registerEvents(new StaminaListener(this), plugin);
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
    public Map<UUID, Long> getConsumableDelayMapCopy() {
        return Map.copyOf(getConsumableDelay());
    }

    @Override
    public Map<ItemStack, IConsumableItem> getConsumableItemMapCopy() {
        return Map.copyOf(getConsumableItem());
    }


    public Map<UUID, Long> getConsumableDelay() {
        return consumableDelay;
    }

    public Map<ItemStack, IConsumableItem> getConsumableItem() {
        return consumableItem;
    }

    public Map<UUID, StaminaPlayer> getStaminaPlayerMap() {
        return staminaPlayerMap;
    }

    public IConfig getConfig() {
        return config;
    }

    private void updateStaminaPlayers() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.getPlugin(), () -> {
            for(StaminaPlayer staminaPlayer : staminaPlayerMap.values()) {
                Player player = Bukkit.getPlayer(staminaPlayer.getUuid());

                if(player.getGameMode().equals(GameMode.CREATIVE)) {
                    continue;
                }

                if(player.isSprinting()) {
                    staminaPlayer.setActive(true);
                    double newStamina = staminaPlayer.getCurrentStaminaLevel() - config.staminaSettings().tireRate();
                    if(newStamina <= 0.0) {
                        staminaPlayer.setCurrentStaminaLevel(0.0);
                        player.setSprinting(false);
                    } else {
                        staminaPlayer.setCurrentStaminaLevel(newStamina);
                    }
                } else {
                    staminaPlayer.setActive(false);
                }
                if(staminaPlayer.getCurrentStaminaLevel() <= config.staminaSettings().slownessThreshold()) {
                    if(!player.hasPotionEffect(PotionEffectType.SLOW)) {
                        player.addPotionEffect(slownessPotion);
                    }
                    if(!player.hasPotionEffect(PotionEffectType.JUMP)) {
                        player.addPotionEffect(jumpDenyPotion);
                    }
                } else {
                    if(player.hasPotionEffect(PotionEffectType.SLOW)) {
                        player.removePotionEffect(PotionEffectType.SLOW);
                    }
                    if(player.hasPotionEffect(PotionEffectType.JUMP)) {
                        player.removePotionEffect(PotionEffectType.JUMP);
                    }
                }

                float xpMultiplier = (float) (1.0f / staminaPlayer.getMaxStaminaLevel());
                player.setExp((float) staminaPlayer.getCurrentStaminaLevel() * xpMultiplier);
            }
        }, 0L, 1L);

    }

}

package com.github.beastyboo.warzreloaded.application;

import com.github.beastyboo.warzreloaded.api.WarZAPI;
import com.github.beastyboo.warzreloaded.entity.Zone;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WarZ implements WarZAPI {

    final JavaPlugin plugin;
    private final Logger logger;
    private final Map<String, Zone> zoneMap;

    WarZ(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
        zoneMap = new HashMap<>();
    }

    void load() {

    }

    void close() {

    }

    @Override
    public boolean createZone(String name) {
        if(getZone(name) != null) {
            return false;
        }

        Zone zone = new Zone(name);
        zoneMap.put(name.toLowerCase(), zone);
        return true;
    }

    @Override
    public boolean deleteZone(String name) {
        Zone zone = getZone(name);

        if(zone == null) {
            return false;
        }

        zoneMap.remove(name.toLowerCase(), zone);
        return true;
    }

    @Override
    public void sendZoneList(CommandSender sender) {
        sender.sendMessage("Zones");
        for(Zone zone : zoneMap.values()) {
            sender.sendMessage("    - " + zone.getName());
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public Zone getZone(String name) {
        return zoneMap.get(name.toLowerCase());
    }

    public Map<String, Zone> getZoneMap() {
        return zoneMap;
    }
}

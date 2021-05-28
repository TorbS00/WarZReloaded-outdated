package com.github.beastyboo.warzreloaded.application;

import com.github.beastyboo.warzreloaded.application.entity.Zone;
import org.bukkit.command.CommandSender;

import java.util.Map;

public interface WarZAPI {

    boolean createZone(String name);

    boolean deleteZone(String name);

    void sendZoneList(CommandSender sender);

    Zone getZone(String name);

    Map<String, Zone> getZoneMap();

}

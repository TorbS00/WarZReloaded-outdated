package com.github.beastyboo.warzreloaded.api;

import com.github.beastyboo.warzreloaded.entity.Zone;
import org.bukkit.command.CommandSender;

public interface WarZAPI {

    boolean createZone(String name);

    boolean deleteZone(String name);

    void sendZoneList(CommandSender sender);

    Zone getZone(String name);

}

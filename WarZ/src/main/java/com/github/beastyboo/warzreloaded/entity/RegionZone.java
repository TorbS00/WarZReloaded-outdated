package com.github.beastyboo.warzreloaded.entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class RegionZone {

    private final String regionName;
    private final Zone zone;
    private final Set<UUID> aliveZombies;

    public RegionZone(String regionName, Zone zone) {
        this.regionName = regionName;
        this.zone = zone;
        this.aliveZombies = new HashSet<>();
    }

    public String getRegionName() {
        return regionName;
    }

    public Zone getZone() {
        return zone;
    }

    public Set<UUID> getAliveZombies() {
        return aliveZombies;
    }

    public void spawnZoneZombie(Location location) {
        World world = location.getWorld();
        ZoneZombie zoneZombie = zone.getZoneZombie();

        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(zoneZombie.getDamage());
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(zoneZombie.getSpeed());
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(zoneZombie.getHealth());

        zombie.setHealth(zoneZombie.getHealth());
        aliveZombies.add(zombie.getUniqueId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionZone that = (RegionZone) o;
        return regionName.equals(that.regionName) && zone.equals(that.zone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName, zone);
    }


    @Override
    public String toString() {
        return "RegionZone{" +
                "regionName='" + regionName + '\'' +
                ", zone=" + zone +
                '}';
    }
}

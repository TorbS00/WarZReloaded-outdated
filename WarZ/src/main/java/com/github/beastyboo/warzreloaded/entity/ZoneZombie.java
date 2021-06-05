package com.github.beastyboo.warzreloaded.entity;

import java.util.Objects;
import java.util.Set;

public class ZoneZombie {

    private final double damage;
    private final double speed;
    private final double health;
    private final Set<LootTableItem> zombieLootTable;

    public ZoneZombie(double damage, double speed, double health, Set<LootTableItem> zombieLootTable) {
        this.damage = damage;
        this.speed = speed;
        this.health = health;
        this.zombieLootTable = zombieLootTable;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHealth() {
        return health;
    }

    public Set<LootTableItem> getZombieLootTable() {
        return zombieLootTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneZombie that = (ZoneZombie) o;
        return Double.compare(that.damage, damage) == 0 && Double.compare(that.speed, speed) == 0 && Double.compare(that.health, health) == 0 && zombieLootTable.equals(that.zombieLootTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(damage, speed, health, zombieLootTable);
    }

    @Override
    public String toString() {
        return "ZoneZombie{" +
                "damage=" + damage +
                ", speed=" + speed +
                ", health=" + health +
                ", zombieLootTable=" + zombieLootTable +
                '}';
    }
}

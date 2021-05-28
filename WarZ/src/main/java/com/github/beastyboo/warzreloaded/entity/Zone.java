package com.github.beastyboo.warzreloaded.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Zone {

    private final String name;
    private final Set<LootTableItem> zoneLootTable;

    public Zone(String name) {
        this.name = name;
        zoneLootTable = new HashSet<>();
    }

    public Zone(String name, Set<LootTableItem> zoneLootTable) {
        this.name = name;
        this.zoneLootTable = zoneLootTable;
    }

    public String getName() {
        return name;
    }

    public Set<LootTableItem> getZoneLootTable() {
        return zoneLootTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return name.equals(zone.name) && zoneLootTable.equals(zone.zoneLootTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, zoneLootTable);
    }

    @Override
    public String toString() {
        return "Zone{" +
                "name='" + name + '\'' +
                ", zoneLootTable=" + zoneLootTable +
                '}';
    }
}

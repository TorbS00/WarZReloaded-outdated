package com.github.beastyboo.warzguns.calculator;

import com.github.beastyboo.warzguns.WarZGuns;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class DamageCalculator {

    private final WarZGuns core;
    private final DecimalFormat decimalFormat;

    public DamageCalculator(WarZGuns core) {
        this.core = core;
        this.decimalFormat = new DecimalFormat("#.##");
    }

    public void runDamageCalculatorAsync(Player player, double baseDamage, int armor, int armorToughness) {
        core.getExecutor().execute(runDamageCalculator(player, baseDamage, armor, armorToughness));
    }

    public Runnable runDamageCalculator(Player player, double baseDamage, int armor, int armorToughness) {
        return () -> {
            player.sendMessage("§cDamage taken would be: " + getCalculatedDamage(baseDamage, armor, armorToughness));
        };
    }

    public String getCalculatedDamage(double baseDamage, int armor, int armorToughness) {
        return decimalFormat.format(baseDamage * (1 -((Math.min(20, Math.max(armor/5, armor-((4*baseDamage)/(armorToughness+8)))))/25)));
    }

}

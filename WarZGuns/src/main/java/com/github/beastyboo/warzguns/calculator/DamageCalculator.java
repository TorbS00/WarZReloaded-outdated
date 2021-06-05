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

    /**
     * Execute the method thru our thread pool in order to not occupy the main thread.
     * The math in this method is honestly safe to run on the main thread, and the Player
     * object is not thread-safe. Therefore runDamageCalculator(...) is the preferred choice.
     *
     * @param player receiver
     * @param baseDamage attack/gun default damage
     * @param armor armor defense (0-20)
     * @param armorToughness armor toughness (0-12)
     */
    public void runDamageCalculatorAsync(Player player, double baseDamage, int armor, int armorToughness) {
        core.getExecutor().execute(runDamageCalculator(player, baseDamage, armor, armorToughness));
    }

    /**
     * Calculate the damage a player would receive using minecraft's algorithm.
     * Method executed as a Runnable in order to be used async from our thread-pool.
     *
     * @param player receiver
     * @param baseDamage attack/gun default damage
     * @param armor armor defense (0-20)
     * @param armorToughness armor toughness (0-12)
     * @return formatted version of the calculated damage (#.##)
     */
    public Runnable runDamageCalculator(Player player, double baseDamage, int armor, int armorToughness) {
        return () -> {
            player.sendMessage("§cDamage taken would be: " + getCalculatedDamage(baseDamage, armor, armorToughness));
        };
    }

    /**
     * Calculate the damage a player would receive using minecraft's algorithm.
     *
     * @param baseDamage attack/gun default damage
     * @param armor armor defense (0-20)
     * @param armorToughness armor toughness (0-12)
     * @return formatted version of the calculated damage (#.##)
     */
    public String getCalculatedDamage(double baseDamage, int armor, int armorToughness) {
        return decimalFormat.format(baseDamage * (1 -((Math.min(20, Math.max(armor/5, armor-((4*baseDamage)/(armorToughness+8)))))/25)));
    }
}

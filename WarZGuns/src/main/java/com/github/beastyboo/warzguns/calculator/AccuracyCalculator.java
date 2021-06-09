package com.github.beastyboo.warzguns.calculator;

import org.bukkit.util.Vector;

import java.util.Random;

public class AccuracyCalculator {

    private final Random random;

    public AccuracyCalculator() {
        this.random = new Random();
    }

    public Vector calculateAccuracy(Vector vector, double accuracy) {
        if(accuracy == 100) {
            return vector;
        }

        double offset = 1 - (accuracy/100);

        return new Vector(calculateNewValue(vector.getX(), offset), calculateNewValue(vector.getY(), offset), calculateNewValue(vector.getZ(), offset));
    }

    private double calculateNewValue(double value, double offset) {
        double max = value + offset;
        double min = value - offset;
        return random.nextDouble()*(max-min) + min;
    }
}

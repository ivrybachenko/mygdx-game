package com.mygdx.game.generation.primitive;

import com.badlogic.gdx.math.MathUtils;

public class RandomIntSupplier implements IntSupplier {
    private final int minValue;
    private final int maxValue;

    public RandomIntSupplier(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public int getValue() {
        return MathUtils.random(minValue, maxValue);
    }
}

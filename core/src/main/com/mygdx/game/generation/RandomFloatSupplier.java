package com.mygdx.game.generation;

import com.badlogic.gdx.math.MathUtils;

public class RandomFloatSupplier implements FloatSupplier {

    private final float minValue;
    private final float maxValue;

    public RandomFloatSupplier(float minValue, float maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public float getValue() {
        return MathUtils.random(minValue, maxValue);
    }
}

package com.mygdx.game.generation;

public class StaticFloatSupplier implements FloatSupplier {

    private final float value;

    public StaticFloatSupplier(float value) {
        this.value = value;
    }

    @Override
    public float getValue() {
        return value;
    }
}

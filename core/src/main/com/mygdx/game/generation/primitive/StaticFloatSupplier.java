package com.mygdx.game.generation.primitive;

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

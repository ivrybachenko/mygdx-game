package com.mygdx.game.generation;

import com.mygdx.game.generation.primitive.FloatSupplier;
import com.mygdx.game.generation.primitive.IntSupplier;

public class AutoUpdatableInt {

    private final IntSupplier valueSupplier;
    private final FloatSupplier timeoutSupplier;

    private int value;
    private float timeElapsed;
    private float timeout;

    public AutoUpdatableInt(IntSupplier valueSupplier, FloatSupplier timeoutSupplier) {
        this.valueSupplier = valueSupplier;
        this.timeoutSupplier = timeoutSupplier;
        changeValue();
    }

    public int getValue() {
        return value;
    }

    public void commitTime(float time) {
        timeElapsed += time;
        if (timeElapsed >= timeout) {
            changeValue();
        }
    }

    private void changeValue() {
        timeElapsed = 0;
        value = valueSupplier.getValue();
        timeout = timeoutSupplier.getValue();
    }
}

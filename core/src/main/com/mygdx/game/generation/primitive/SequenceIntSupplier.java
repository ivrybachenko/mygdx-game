package com.mygdx.game.generation.primitive;

import com.badlogic.gdx.utils.Array;

public class SequenceIntSupplier implements IntSupplier {

    private final Array<Integer> values;
    private int nextIndex = 0;

    public SequenceIntSupplier(Array<Integer> values) {
        this.values = values;
    }

    @Override
    public int getValue() {
        int value = values.get(nextIndex);
        updateNextIndex();
        return value;
    }

    private void updateNextIndex() {
        nextIndex++;
        if (nextIndex == values.size) {
            nextIndex = 0;
        }
    }
}

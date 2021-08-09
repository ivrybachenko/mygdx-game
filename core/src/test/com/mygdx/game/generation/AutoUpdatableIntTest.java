package com.mygdx.game.generation;

import com.badlogic.gdx.utils.Array;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AutoUpdatableIntTest {

    @Test
    void test1() {
        Array<Integer> values = new Array<>();
        values.addAll(1, 2);
        AutoUpdatableInt autoUpdatableInt = new AutoUpdatableInt(new SequenceIntSupplier(values),
                new StaticFloatSupplier(100));

        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(1);
        autoUpdatableInt.commitTime(99);
        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(1);
        autoUpdatableInt.commitTime(1);
        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(2);
    }

}
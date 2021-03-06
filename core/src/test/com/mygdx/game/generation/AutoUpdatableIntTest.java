package com.mygdx.game.generation;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.generation.primitive.SequenceIntSupplier;
import com.mygdx.game.generation.primitive.StaticFloatSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AutoUpdatableIntTest {

    @Test
    void givesTheFirstValueRightAfterCreation() {
        Array<Integer> values = new Array<>();
        values.addAll(1, 2);
        AutoUpdatableInt autoUpdatableInt = new AutoUpdatableInt(new SequenceIntSupplier(values),
                new StaticFloatSupplier(100));

        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(1);
    }

    @Test
    void givesTheFirstValueBeforeTimeout() {
        Array<Integer> values = new Array<>();
        values.addAll(1, 2);
        AutoUpdatableInt autoUpdatableInt = new AutoUpdatableInt(new SequenceIntSupplier(values),
                new StaticFloatSupplier(100));

        autoUpdatableInt.commitTime(99);

        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(1);
    }

    @Test
    void givesTheSecondValueAfterTimeout() {
        Array<Integer> values = new Array<>();
        values.addAll(1, 2);
        AutoUpdatableInt autoUpdatableInt = new AutoUpdatableInt(new SequenceIntSupplier(values),
                new StaticFloatSupplier(100));

        autoUpdatableInt.commitTime(100);

        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(2);
    }


    @Test
    void sumsCommittedTime() {
        Array<Integer> values = new Array<>();
        values.addAll(1, 2);
        AutoUpdatableInt autoUpdatableInt = new AutoUpdatableInt(new SequenceIntSupplier(values),
                new StaticFloatSupplier(100));

        autoUpdatableInt.commitTime(99);
        autoUpdatableInt.commitTime(1);

        assertThat(autoUpdatableInt.getValue())
                .isEqualTo(2);
    }
}
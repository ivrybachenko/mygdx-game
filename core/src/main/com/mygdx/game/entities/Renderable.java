package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Renderable {
    void render(Batch batch, float timeDelta);
}

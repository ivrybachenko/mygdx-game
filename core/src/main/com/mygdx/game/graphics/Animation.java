package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;

public class Animation {
    private final Texture texture;
    private float duration;

    public Animation(Texture texture, float duration) {
        this.texture = texture;
        this.duration = duration;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}

package com.mygdx.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ControlsMapping {

    public boolean cancel() {
        return Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }

    public boolean moveLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    public boolean moveRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public boolean sitDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    public boolean jump() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}

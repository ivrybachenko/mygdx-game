package com.mygdx.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ControlsMapping {

    public boolean cancel() {
        return Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }

    public boolean moveLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public boolean moveRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public boolean sitDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                Gdx.input.isKeyPressed(Input.Keys.S);
    }

    public boolean jump() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE) ||
                Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.W);
    }
}

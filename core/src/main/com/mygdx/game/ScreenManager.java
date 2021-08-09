package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.util.Stack;

public class ScreenManager {

    private final Game game;
    private final Stack<Screen> screens;
    private static final String LOG_TAG = "ScreenManager";

    public ScreenManager(Game game) {
        this.game = game;
        this.screens = new Stack<>();
    }

    public void push(Screen screen) {
        Gdx.app.log(LOG_TAG, "Push screen " + screen.getClass().getName());
        screens.push(screen);
        applyLastScreen();
    }

    public void pop() {
        Gdx.app.log(LOG_TAG, "Pop screen");
        if (screens.size() == 1) {
            Gdx.app.error(LOG_TAG, "Nothing to pop");
            return;
        }
        screens.pop();
        applyLastScreen();
        Gdx.app.log(LOG_TAG, "New screen is " +
                this.screens.lastElement().getClass().getName());
    }

    private void applyLastScreen() {
        game.setScreen(screens.lastElement());
    }
}

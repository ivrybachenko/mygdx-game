package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.LogoScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.service.ControlsMapping;
import com.mygdx.game.service.ScreenManager;

public class DropGame extends Game {

    public ScreenManager screenManager;
    public ControlsMapping controlsMapping;
    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        controlsMapping = new ControlsMapping();
        screenManager = new ScreenManager(this);
        screenManager.push(new MainMenuScreen(this));
        screenManager.push(new LogoScreen(this,
                new Texture(Gdx.files.internal("logo_simplegame.png")), 3));
        screenManager.push(new LogoScreen(this,
                new Texture(Gdx.files.internal("logo_gaijin.png")), 3));
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

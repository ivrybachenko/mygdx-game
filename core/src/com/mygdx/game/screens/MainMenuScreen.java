package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.DropGame;

public class MainMenuScreen implements Screen {

    final DropGame game;

    OrthographicCamera camera;
    private Texture backgroundTexture;

    public MainMenuScreen(final DropGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        backgroundTexture = new Texture(Gdx.files.internal("menu_background.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        Rectangle texRect = fitTextureIntoTheScreen(800, 480);
        game.batch.draw(backgroundTexture, texRect.x, texRect.y, texRect.width, texRect.height);
        game.font.draw(game.batch, "Welcome to Drop!!!", 420, 250);
        game.font.draw(game.batch, "Tap anywhere to begin!", 420, 200);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    private Rectangle fitTextureIntoTheScreen(int screenWidth, int screenHeight) {
        double scaleFactor = Math.min((double) screenWidth / backgroundTexture.getWidth(),
                (double) screenHeight / backgroundTexture.getHeight());
        int width = (int) (backgroundTexture.getWidth() * scaleFactor);
        int height = (int) (backgroundTexture.getHeight() * scaleFactor);
        return new Rectangle((screenWidth - width) / 2, (screenHeight - height) / 2,
                width, height);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

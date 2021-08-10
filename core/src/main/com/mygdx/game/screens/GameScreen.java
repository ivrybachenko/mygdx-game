package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.DropGame;
import com.mygdx.game.entities.Monster;
import com.mygdx.game.entities.Spearman;

public class GameScreen implements Screen {

    final DropGame game;

    private Monster monster;
    private Array<Spearman> spearmen;

    private Texture backgroundImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private long lastSpearmanTime;
    private int score = 0;

    public GameScreen(final DropGame game) {
        this.game = game;
        batch = game.batch;
        monster = new Monster(new Vector2(800 / 2 - 64 / 2, 25));

        backgroundImage = new Texture(Gdx.files.internal("background.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        spearmen = new Array<>();
        spawnSpearman();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundImage, 0, 0);
        monster.render(batch, delta);
        game.font.draw(game.batch, "Score: " + score, 720, 460);
        for (Spearman spearman : spearmen) {
            spearman.render(batch, delta);
        }
        batch.end();

        handleKeyboardInput(delta);

        if (TimeUtils.nanoTime() - lastSpearmanTime > 1000000000) {
            spawnSpearman();
        }

        for (Spearman spearman : spearmen) {
            if (spearman.getBoundingBox().x < monster.getBoundingBox().x + spearman.getTargetMonsterDistance()) {
                spearman.moveRight(delta);
            }
            if (spearman.getBoundingBox().x > monster.getBoundingBox().x + spearman.getTargetMonsterDistance()) {
                spearman.moveLeft(delta);
            }
        }
    }

    private void handleKeyboardInput(float timeDelta) {
        if (game.controlsMapping.cancel()) {
            game.screenManager.push(new MainMenuScreen(game));
        }
        if (game.controlsMapping.moveLeft()) {
            monster.moveLeft(timeDelta);
        }
        if (game.controlsMapping.moveRight()) {
            monster.moveRight(timeDelta);
        }
        if (game.controlsMapping.sitDown()) {
            monster.setSiting(true);
        } else {
            monster.setSiting(false);
        }
        if (game.controlsMapping.jump()) {
            if (monster.getBoundingBox().y == 20) {
                monster.jump();
            }
        }
        handleOutsideTheScreen();
    }

    private void handleOutsideTheScreen() {
        if (monster.getBoundingBox().x < 0) {
            monster.getBoundingBox().x = 0;
        }
        if (monster.getBoundingBox().x > 800 - 64) {
            monster.getBoundingBox().x = 800 - 64;
        }
    }


    private void spawnSpearman() {
        Vector2 position = new Vector2();
        if (MathUtils.randomSign() > 0) {
            position.x = 20;
        } else {
            position.x = 780;
        }
        position.y = MathUtils.random(20, 40);
        spearmen.add(new Spearman(position));
        lastSpearmanTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {

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
        batch.dispose();
    }
}

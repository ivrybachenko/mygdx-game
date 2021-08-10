package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.DropGame;
import com.mygdx.game.graphics.Resizer;

public class LogoScreen extends ScreenAdapter {

    private static final float MIN_TIME_BEFORE_SKIP = 0.5f;
    private final Texture backgroundTexture;
    private final DropGame game;
    private final float timeout;
    private float timeElapsed = 0;
    private boolean skipScreenKeyWasPressed = false;

    public LogoScreen(DropGame game, Texture backgroundTexture, long timeout) {
        this.game = game;
        this.backgroundTexture = backgroundTexture;
        this.timeout = timeout;
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            skipScreenKeyWasPressed = true;
        }
        timeElapsed += delta;
        if (screenShouldBeChanged()) {
            game.screenManager.pop();
        } else {
            drawBackgroundTexture();
        }
    }

    private boolean screenShouldBeChanged() {
        return timeElapsed >= timeout ||
                (skipScreenKeyWasPressed && timeElapsed > MIN_TIME_BEFORE_SKIP);
    }

    private void drawBackgroundTexture() {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        game.batch.begin();
        Rectangle texRect = Resizer.fitTextureIntoRectangle(backgroundTexture, 800, 480);
        game.batch.draw(backgroundTexture, texRect.x, texRect.y, texRect.width, texRect.height);
        game.font.draw(game.batch, "Press any key to skip...", 20, 20);
        game.batch.end();
    }


    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}

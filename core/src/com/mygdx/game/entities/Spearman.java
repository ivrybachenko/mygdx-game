package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Spearman implements Renderable {

    private final static Texture TEXTURE_LEFT =
            new Texture(Gdx.files.internal("heroes/spearman_left.png"));
    private final static Texture TEXTURE_RIGHT =
            new Texture(Gdx.files.internal("heroes/spearman_right.png"));
    private Texture currentTexture = TEXTURE_RIGHT;
    private final Rectangle boundingBox;
    private int movementSpeed = 200;
    public float dx = MathUtils.random(-200, 200);
    public float timeSinceDxWasChanged = 0;
    private float TIMEOUT_TO_CHANGE_DX = 2;

    public Spearman(Vector2 position) {
        boundingBox = new Rectangle();
        boundingBox.x = position.x;
        boundingBox.y = position.y;
        boundingBox.width = 16;
        boundingBox.height = 16;
    }

    @Override
    public void render(Batch batch, float timeDelta) {
        timeSinceDxWasChanged += timeDelta;
        if (timeSinceDxWasChanged > TIMEOUT_TO_CHANGE_DX) {
            timeSinceDxWasChanged = 0;
            dx = MathUtils.random(-100, 250);
            TIMEOUT_TO_CHANGE_DX = MathUtils.random(0, 3);
        }
        batch.draw(currentTexture, boundingBox.x, boundingBox.y);
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public void moveLeft(float timeDelta) {
        currentTexture = TEXTURE_LEFT;
        this.getBoundingBox().x -= movementSpeed * timeDelta;
    }

    public void moveRight(float timeDelta) {
        currentTexture = TEXTURE_RIGHT;
        this.getBoundingBox().x += movementSpeed * timeDelta;
    }

}

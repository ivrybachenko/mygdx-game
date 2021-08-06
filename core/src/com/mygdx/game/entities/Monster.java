package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Monster implements Renderable {

    private final static Texture TEXTURE_FRONT =
            new Texture(Gdx.files.internal("heroes/monster_front.png"));
    private final static Texture TEXTURE_SIT =
            new Texture(Gdx.files.internal("heroes/monster_sit.png"));
    private Texture currentTexture = TEXTURE_FRONT;
    private final Rectangle boundingBox;
    private final int movementSpeed = 200;
    private boolean isSiting = false;

    public Monster(Vector2 position) {
        boundingBox = new Rectangle();
        boundingBox.x = position.x;
        boundingBox.y = position.y;
        boundingBox.width = 64;
        boundingBox.height = 64;
    }

    @Override
    public void render(Batch batch, float timeDelta) {
        batch.draw(currentTexture, boundingBox.x, boundingBox.y);
    }

    public void moveLeft(float timeDelta) {
        getBoundingBox().x -= movementSpeed * timeDelta;
    }

    public void moveRight(float timeDelta) {
        getBoundingBox().x += movementSpeed * timeDelta;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setSiting(boolean siting) {
        isSiting = siting;
        if (isSiting) {
            currentTexture = TEXTURE_SIT;
        } else {
            currentTexture = TEXTURE_FRONT;
        }
    }
}

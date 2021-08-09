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
    private final static Texture TEXTURE_JUMP =
            new Texture(Gdx.files.internal("heroes/monster_jump.png"));

    private Texture currentTexture = TEXTURE_FRONT;
    private final Rectangle boundingBox;
    private final int movementSpeed = 200;
    private boolean isSiting = false;
    private float   velocityY = 0;

    public Monster(Vector2 position) {
        boundingBox = new Rectangle();
        boundingBox.x = position.x;
        boundingBox.y = position.y;
        boundingBox.width = 64;
        boundingBox.height = 64;
    }

    @Override
    public void render(Batch batch, float timeDelta) {
        if (boundingBox.y > 20 || velocityY > 0) {
            velocityY -= 1;
            boundingBox.y += velocityY;
            currentTexture = TEXTURE_JUMP;
        } else {
            velocityY = 0;
            boundingBox.y = 20;
        }
        batch.draw(currentTexture, boundingBox.x, boundingBox.y);
    }

    public void jump() {
        this.velocityY = 14;
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

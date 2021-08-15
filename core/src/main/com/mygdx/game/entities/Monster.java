package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.graphics.Animation;
import com.mygdx.game.graphics.AnimationStack;

public class Monster implements Renderable {

    private final static Texture TEXTURE_FRONT =
            new Texture(Gdx.files.internal("heroes/monster_front.png"));
    private final static Texture TEXTURE_SIT =
            new Texture(Gdx.files.internal("heroes/monster_sit.png"));
    private final static Texture TEXTURE_JUMP =
            new Texture(Gdx.files.internal("heroes/monster_jump.png"));
    private final static Texture TEXTURE_HIT_LEFT =
            new Texture(Gdx.files.internal("heroes/monster_hit_left.png"));
    private final static Texture TEXTURE_HIT_RIGHT =
            new Texture(Gdx.files.internal("heroes/monster_hit_right.png"));

    private AnimationStack animationStack = new AnimationStack();
    private Texture currentTexture = TEXTURE_FRONT;
    private final Rectangle boundingBox;
    private final int movementSpeed = 200;
    private boolean isSiting = false;
    private float velocityY = 0;
    private int life = 100;
    private float hitTimeout = 0;
    private final float ATTACK_RESET_TIME = 0.5f;

    public Monster(Vector2 position) {
        boundingBox = new Rectangle();
        boundingBox.x = position.x;
        boundingBox.y = position.y;
        boundingBox.width = 64;
        boundingBox.height = 64;
    }

    @Override
    public void render(Batch batch, float timeDelta) {
        if (!animationStack.isEmpty()) {
            animationStack.commitTime(timeDelta);
        }
        if (!animationStack.isEmpty()) {
            currentTexture = animationStack.getCurrentTexture();
        } else {
            hitTimeout -= timeDelta;
        }
        if (boundingBox.y > 20 || velocityY > 0) {
            velocityY -= 1;
            boundingBox.y += velocityY;
            if (animationStack.isEmpty()) {
                currentTexture = TEXTURE_JUMP;
            }
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

    public void hitLeft() {
        if (canHit()) {
            animationStack.push(new Animation(TEXTURE_HIT_LEFT, 0.4f));
            hitTimeout = ATTACK_RESET_TIME;
        }
    }

    public void hitRight() {
        if (canHit()) {
            animationStack.push(new Animation(TEXTURE_HIT_RIGHT, 0.4f));
            hitTimeout = ATTACK_RESET_TIME;
        }
    }

    private boolean canHit() {
        return animationStack.isEmpty() && hitTimeout <= 0;
    }

    public void takeDamage() {
        life--;
        if (life < 0) {
            life = 0;
        }
    }

    public boolean isHitLeft() {
        if (animationStack.isEmpty()) {
            return false;
        }
        return animationStack.getCurrentTexture() == TEXTURE_HIT_LEFT;
    }

    public boolean isHitRight() {
        if (animationStack.isEmpty()) {
            return false;
        }
        return animationStack.getCurrentTexture() == TEXTURE_HIT_RIGHT;
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

    public int getLife() {
        return life;
    }
}

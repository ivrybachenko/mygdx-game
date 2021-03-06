package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.generation.AutoUpdatableInt;
import com.mygdx.game.generation.primitive.FloatSupplier;
import com.mygdx.game.generation.primitive.IntSupplier;
import com.mygdx.game.generation.primitive.RandomFloatSupplier;
import com.mygdx.game.generation.primitive.RandomIntSupplier;

public class Spearman implements Renderable {

    private static final Texture TEXTURE_LEFT =
            new Texture(Gdx.files.internal("heroes/spearman_left.png"));
    private static final Texture TEXTURE_RIGHT =
            new Texture(Gdx.files.internal("heroes/spearman_right.png"));
    private static final Texture TEXTURE_DEAD_LEFT =
            new Texture(Gdx.files.internal("heroes/spearman_dead_left.png"));
    private static final Texture TEXTURE_DEAD_RIGHT =
            new Texture(Gdx.files.internal("heroes/spearman_dead_right.png"));

    private Texture currentTexture = TEXTURE_RIGHT;
    private final Rectangle boundingBox;
    private final int MOVEMENT_SPEED = 200;
    private final AutoUpdatableInt targetMonsterDistance;
    private boolean isDead = false;

    private static final IntSupplier TARGET_MONSTER_DISTANCE_SUPPLIER = new RandomIntSupplier(-100, 250);
    private static final FloatSupplier TARGET_MONSTER_DISTANCE_TIMEOUT_SUPPLIER =
            new RandomFloatSupplier(1.5f, 3f);

    public Spearman(Vector2 position) {
        boundingBox = new Rectangle();
        boundingBox.x = position.x;
        boundingBox.y = position.y;
        boundingBox.width = 16;
        boundingBox.height = 16;

        targetMonsterDistance = new AutoUpdatableInt(TARGET_MONSTER_DISTANCE_SUPPLIER,
                TARGET_MONSTER_DISTANCE_TIMEOUT_SUPPLIER);
    }

    @Override
    public void render(Batch batch, float timeDelta) {
        targetMonsterDistance.commitTime(timeDelta);
        batch.draw(currentTexture, boundingBox.x, boundingBox.y);
    }

    public int getTargetMonsterDistance() {
        return targetMonsterDistance.getValue();
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public void moveLeft(float timeDelta) {
        if (isDead) {
            return;
        }
        currentTexture = TEXTURE_LEFT;
        this.getBoundingBox().x -= MOVEMENT_SPEED * timeDelta;
    }

    public void moveRight(float timeDelta) {
        if (isDead) {
            return;
        }
        currentTexture = TEXTURE_RIGHT;
        this.getBoundingBox().x += MOVEMENT_SPEED * timeDelta;
    }

    public void die() {
        isDead = true;
        if (MathUtils.randomSign() > 0) {
            currentTexture = TEXTURE_DEAD_LEFT;
        } else {
            currentTexture = TEXTURE_DEAD_RIGHT;
        }
    }

    public boolean isDead() {
        return  isDead;
    }

}

package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Resizer {

    public static Rectangle fitTextureIntoRectangle(Texture texture, int targetWidth, int targetHeight) {
        double scaleFactor = Math.min((double) targetWidth / texture.getWidth(),
                (double) targetHeight / texture.getHeight());
        int width = (int) (texture.getWidth() * scaleFactor);
        int height = (int) (texture.getHeight() * scaleFactor);
        float x = (targetWidth - width) / 2f;
        float y = (targetHeight - height) / 2f;
        return new Rectangle(x, y, width, height);
    }
}

package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Queue;

public class AnimationStack {

    private final Queue<Animation> stack = new Queue<>();

    public void push(Animation animation) {
        stack.addLast(animation);
    }

    public void commitTime(float time) {
        stack.last().setDuration(stack.last().getDuration()-time);
        if (stack.last().getDuration() < 0) {
            stack.removeLast();
        }
    }

    public Texture getCurrentTexture() {
        return stack.last().getTexture();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

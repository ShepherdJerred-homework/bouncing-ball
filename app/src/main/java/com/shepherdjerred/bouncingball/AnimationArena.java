package com.shepherdjerred.bouncingball;

import android.graphics.Canvas;

public class AnimationArena {
    private Ball mBall;
    private int mWidth;
    private int mHeight;

    public AnimationArena(int arenaWidth, int arenaHeight) {
        mWidth = arenaWidth;
        mHeight = arenaHeight;
        mBall = new Ball(mWidth, mHeight);
    }

    public void update(int velocityX, int velocityY) {
        // Move the ball
        mBall.move(velocityX, velocityY);
    }

    public void draw(Canvas canvas) {
        // Wipe canvas clean
        canvas.drawRGB(255, 255, 255);

        mBall.draw(canvas);
    }
}

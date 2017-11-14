package com.zybooks.bouncingball;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class BounceThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private AnimationArena mAnimationArena;
    private boolean isRunning;

    public BounceThread(SurfaceHolder sh) {
        isRunning = true;
        mSurfaceHolder = sh;
        Canvas canvas = mSurfaceHolder.lockCanvas();
        mAnimationArena = new AnimationArena(canvas.getWidth(), canvas.getHeight());
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void run() {
        try {
            while (isRunning) {
                Canvas canvas = mSurfaceHolder.lockCanvas();
                mAnimationArena.update();
                mAnimationArena.draw(canvas);
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void endBounce() {
        isRunning = false;
    }
}

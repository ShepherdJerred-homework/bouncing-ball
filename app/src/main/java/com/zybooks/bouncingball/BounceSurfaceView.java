package com.zybooks.bouncingball;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BounceSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private BounceThread mBounceThread;

    public BounceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mBounceThread = new BounceThread(surfaceHolder);
        mBounceThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mBounceThread.endBounce();
        Thread dummy = mBounceThread;
        mBounceThread = null;
        dummy.interrupt();
    }
}

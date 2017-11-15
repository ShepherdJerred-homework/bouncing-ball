package com.shepherdjerred.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

public class BounceThread extends Thread implements SensorEventListener {

    private SurfaceHolder mSurfaceHolder;
    private AnimationArena mAnimationArena;
    private boolean isRunning;

    private Context mContext;

    private SensorManager mSensorManager;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private Sensor mAccelerometer;

    private float mSensorX;
    private float mSensorY;

    public BounceThread(Context context, SurfaceHolder sh) {
        mContext = context;
        isRunning = true;
        mSurfaceHolder = sh;
        Canvas canvas = mSurfaceHolder.lockCanvas();
        mAnimationArena = new AnimationArena(canvas.getWidth(), canvas.getHeight());
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void run() {

        mSensorManager = (SensorManager) mContext.getSystemService(SENSOR_SERVICE);
        mWindowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();


        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        try {
            while (isRunning) {
                Canvas canvas = mSurfaceHolder.lockCanvas();
                mAnimationArena.update(Math.round(mSensorX), Math.round(mSensorY));
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;

            /*
             * record the accelerometer data, the event's timestamp as well as
             * the current time. The latter is needed so we can calculate the
             * "present" time during rendering. In this application, we need to
             * take into account how the screen is rotated with respect to the
             * sensors (which always return data in a coordinate space aligned
             * to with the screen in its native orientation).
             */

        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

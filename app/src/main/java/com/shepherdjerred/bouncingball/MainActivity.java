package com.shepherdjerred.bouncingball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.shepherdjerred.bouncingball.R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(com.shepherdjerred.bouncingball.R.id.frameLayout);

        BounceSurfaceView bounceSurfaceView = new BounceSurfaceView(this, null);
        frameLayout.addView(bounceSurfaceView);
    }
}

package com.akexorcist.myapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView ivAndroidLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Multi window code is in AndroidManifest.xml, not here.
        ivAndroidLogo = (ImageView) findViewById(R.id.iv_android_logo);
        ivAndroidLogo.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            scaleDown();
        } else if (action == MotionEvent.ACTION_UP) {
            scaleDefault();
        }
        return true;
    }

    private void scaleDown() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_scale_down);
        animator.setTarget(ivAndroidLogo);
        animator.start();
    }

    private void scaleDefault() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_scale_default);
        animator.setTarget(ivAndroidLogo);
        animator.start();
    }
}

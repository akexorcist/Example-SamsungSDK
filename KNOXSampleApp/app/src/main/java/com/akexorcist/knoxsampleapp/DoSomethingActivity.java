package com.akexorcist.knoxsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);
        bindView();
        setupView();
    }

    private void bindView() {
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
    }

    private void setupView() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn1) {
            onButton1Pressed();
        } else if (v == btn2) {
            onButton2Pressed();
        } else if (v == btn3) {
            onButton3Pressed();
        } else if (v == btn4) {
            onButton4Pressed();
        }
    }

    private void onButton1Pressed() {
        // TODO Do something when button 1 was pressed
    }

    private void onButton2Pressed() {
        // TODO Do something when button 2 was pressed
    }

    private void onButton3Pressed() {
        // TODO Do something when button 3 was pressed
    }

    private void onButton4Pressed() {
        // TODO Do something when button 4 was pressed
    }

}

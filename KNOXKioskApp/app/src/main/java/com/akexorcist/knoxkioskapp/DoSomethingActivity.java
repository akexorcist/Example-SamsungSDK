package com.akexorcist.knoxkioskapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnKioskStart;
    private Button btnKioskStop;

    // TODO Declare kiosk mode service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);

        bindView();
        setupView();
        setupThing();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindView() {
        btnKioskStart = (Button) findViewById(R.id.btn_kiosk_enable);
        btnKioskStop = (Button) findViewById(R.id.btn_kiosk_disable);
    }

    private void setupView() {
        btnKioskStart.setOnClickListener(this);
        btnKioskStop.setOnClickListener(this);
    }

    private void setupThing() {
        // TODO Create kiosk mode service instance

    }

    @Override
    public void onClick(View v) {
        if (v == btnKioskStart) {
            enableKiosk();
        } else if (v == btnKioskStop) {
            disableKiosk();
        }
    }

    private void enableKiosk() {
        setKioskSettings();
        // TODO Start kiosk mode

    }

    private void disableKiosk() {
        clearKioskSettings();
        // TODO Stop kiosk mode

    }

    private void setKioskSettings() {
        // TODO Setup kiosk mode setting

    }

    private void clearKioskSettings() {
        // TODO Clear all kiosk mode setting

    }
}

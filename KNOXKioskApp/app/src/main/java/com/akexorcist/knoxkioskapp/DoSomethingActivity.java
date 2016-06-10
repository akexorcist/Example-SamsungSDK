package com.akexorcist.knoxkioskapp;

import android.app.enterprise.kioskmode.KioskMode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnKioskStart;
    private Button btnKioskStop;

    private KioskMode kioskModeService;

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
        kioskModeService = KioskMode.getInstance(this);
        if (kioskModeService.isKioskModeEnabled()) {
            btnKioskStart.setEnabled(false);
            btnKioskStop.setEnabled(true);
        } else {
            btnKioskStart.setEnabled(true);
            btnKioskStop.setEnabled(false);
        }
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
        setKioskSettings(false);
        kioskModeService.enableKioskMode();
    }

    private void disableKiosk() {
        setKioskSettings(true);
        kioskModeService.disableKioskMode();
    }

    private void setKioskSettings(boolean state) {
        List<Integer> keyList = Arrays.asList(KeyEvent.KEYCODE_HOME, KeyEvent.KEYCODE_BACK);
        kioskModeService.allowHardwareKeys(keyList, state);
        kioskModeService.allowMultiWindowMode(state);
        kioskModeService.allowTaskManager(state);
    }
}

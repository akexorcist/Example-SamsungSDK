package com.akexorcist.knoxgeofencingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGeofencingStart;
    private Button btnGeofencingStop;
    private Button btnCreateGeofence;
    private Button btnClearGeofence;

    // TODO (1) : Declare geofencing service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);

        bindView();
        setupView();
        setupThing();
    }

    private void bindView() {
        btnGeofencingStart = (Button) findViewById(R.id.btn_geofencing_start);
        btnGeofencingStop = (Button) findViewById(R.id.btn_geofencing_stop);
        btnCreateGeofence = (Button) findViewById(R.id.btn_create_geofence);
        btnClearGeofence = (Button) findViewById(R.id.btn_clear_geofence);
    }

    private void setupView() {
        btnGeofencingStart.setOnClickListener(this);
        btnGeofencingStop.setOnClickListener(this);
        btnCreateGeofence.setOnClickListener(this);
        btnClearGeofence.setOnClickListener(this);
    }

    private void setupThing() {
        // TODO (2) : Create geofencing service instance

    }

    @Override
    public void onClick(View v) {
        if (v == btnGeofencingStart) {
            startGeofencing();
            createGeofence();
        } else if (v == btnGeofencingStop) {
            stopGeofencing();
        } else if (v == btnCreateGeofence) {
            createGeofence();
        } else if (v == btnClearGeofence) {
            clearGeofence();
        }
    }

    private void startGeofencing() {
        // TODO (3) : Start geofencing

    }

    private void stopGeofencing() {
        // TODO (4) : Stop geofencing

    }

    private void createGeofence() {
        // TODO (5) : Create geofence, then add into geofencing service

    }

    private void clearGeofence() {
        // TODO (6) : Clear all geofence in geofencing service

    }
}

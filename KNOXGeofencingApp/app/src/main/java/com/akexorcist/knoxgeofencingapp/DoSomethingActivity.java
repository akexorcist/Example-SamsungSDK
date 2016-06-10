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

    // TODO Declare geofencing service

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
        // TODO Create geofencing service instance

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

    private void createGeofence() {
        // TODO Create geofence, then add into geofencing service

    }

    private void clearGeofence() {
        // TODO Clear all geofence in geofencing service

    }

    private void startGeofencing() {
        // TODO Start geofencing

    }

    private void stopGeofencing() {
        // TODO Stop geofencing

    }
}

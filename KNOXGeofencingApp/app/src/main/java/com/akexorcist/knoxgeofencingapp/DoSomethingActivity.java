package com.akexorcist.knoxgeofencingapp;

import android.app.enterprise.geofencing.CircularGeofence;
import android.app.enterprise.geofencing.Geofence;
import android.app.enterprise.geofencing.Geofencing;
import android.app.enterprise.geofencing.LatLongPoint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGeofencingStart;
    private Button btnGeofencingStop;

    private Geofencing geofencingService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);

        bindView();
        setupView();
        setupThing();
        createGeofencingReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearGeofenceReceiver();
    }

    private void bindView() {
        btnGeofencingStart = (Button) findViewById(R.id.btn_geofencing_start);
        btnGeofencingStop = (Button) findViewById(R.id.btn_geofencing_stop);
    }

    private void setupView() {
        btnGeofencingStart.setOnClickListener(this);
        btnGeofencingStop.setOnClickListener(this);
    }

    private void setupThing() {
        geofencingService = Geofencing.getInstance(this);
    }

    private void createGeofencingReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Geofencing.ACTION_DEVICE_INSIDE_GEOFENCE);
        intentFilter.addAction(Geofencing.ACTION_DEVICE_OUTSIDE_GEOFENCE);
        intentFilter.addAction(Geofencing.ACTION_DEVICE_LOCATION_UNAVAILABLE);
        registerReceiver(geofencingReceiver, intentFilter);
    }

    private void clearGeofenceReceiver() {
        unregisterReceiver(geofencingReceiver);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGeofencingStart) {
            startGeofencing();
            createGeofence();
        } else if (v == btnGeofencingStop) {
            stopGeofencing();
        }
    }

    private void startGeofencing() {
        geofencingService.startGeofencing();
    }

    private void stopGeofencing() {
        geofencingService.stopGeofencing();
    }

    private void createGeofence() {
        LatLongPoint center = new LatLongPoint(13.6784555, 100.6149553);
        double radius = 1000;
        CircularGeofence circularGeofence = new CircularGeofence(center, radius);
        geofencingService.createGeofence(circularGeofence);
    }

    private BroadcastReceiver geofencingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_INSIDE_GEOFENCE)) {
                showToast("Device is inside geofence");
                checkGeofenceInfo(intent.getExtras());
            } else if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_OUTSIDE_GEOFENCE)) {
                showToast("Device is outside geofence");
                checkGeofenceInfo(intent.getExtras());
            } else if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_LOCATION_UNAVAILABLE)) {
                showToast("Device location unavailable");
            }
        }
    };

    private void checkGeofenceInfo(Bundle bundle) {
        int[] geofenceIdList = bundle.getIntArray(Geofencing.INTENT_EXTRA_ID);
        if (geofenceIdList != null) {
            for (int id : geofenceIdList) {
                Geofence geofence = getGeofenceById(id);
                if (geofence instanceof CircularGeofence) {
                    getCircularGeofenceInfo(geofence);
                }
            }
        }
    }

    private Geofence getGeofenceById(int id) {
        for (Geofence geofence : geofencingService.getGeofences()) {
            if (geofence.id == id) {
                return geofence;
            }
        }
        return null;
    }

    private void getCircularGeofenceInfo(Geofence geofence) {
        CircularGeofence circularGeofence = (CircularGeofence) geofence;
        showToast("Geofence ID : " + circularGeofence.id +
                " | Type : " + circularGeofence.type +
                " | Center : " + circularGeofence.center.latitude + ", " + circularGeofence.center.longitude +
                " | Radius : " + circularGeofence.radius);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

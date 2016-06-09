package com.akexorcist.knoxgeofencingapp;

import android.app.enterprise.geofencing.CircularGeofence;
import android.app.enterprise.geofencing.Geofence;
import android.app.enterprise.geofencing.Geofencing;
import android.app.enterprise.geofencing.LatLongPoint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.akexorcist.knoxgeofencingapp.bus.BusProvider;
import com.akexorcist.knoxgeofencingapp.bus.event.InsideGeofenceEvent;
import com.akexorcist.knoxgeofencingapp.bus.event.LocationUnavailableEvent;
import com.akexorcist.knoxgeofencingapp.bus.event.OutsideGeofenceEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGeofencingStart;
    private Button btnGeofencingStop;
    private Button btnCreateGeofence;
    private Button btnClearGeofence;

    private Geofencing geofencingService;

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
        BusProvider.getProvider().unregister(this);
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
        BusProvider.getProvider().register(this);
        geofencingService = Geofencing.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGeofencingStart) {
            startGeofencing();
        } else if (v == btnGeofencingStop) {
            stopGeofencing();
        } else if (v == btnCreateGeofence) {
            createGeofence();
        } else if (v == btnClearGeofence) {
            clearGeofence();
        }
    }

    private void createGeofence() {
        LatLongPoint center = new LatLongPoint(13.678505, 100.615474);
        double radius = 1000;
        CircularGeofence circularGeofence = new CircularGeofence(center, radius);
        int id = geofencingService.createGeofence(circularGeofence);
        if (id == -1) {
            Toast.makeText(this, R.string.already_exists_geofence, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.geofence_was_created, id), Toast.LENGTH_SHORT).show();
        }
    }

    private void clearGeofence() {
        List<Geofence> geofenceList = geofencingService.getGeofences();
        if (geofenceList != null) {
            for (Geofence geofence : geofenceList) {
                geofencingService.destroyGeofence(geofence.id);
            }
        }
    }

    private void startGeofencing() {
        geofencingService.startGeofencing();
    }

    private void stopGeofencing() {
        geofencingService.stopGeofencing();
    }

    @Subscribe
    public void onInsideGeofenceEvent(InsideGeofenceEvent event) {
        // When device is inside geofence
    }

    @Subscribe
    public void onOutsideGeofenceEvent(OutsideGeofenceEvent event) {
        // When device is outside geofence
    }

    @Subscribe
    public void onLocationUnavailable(LocationUnavailableEvent event) {
        // When device location is unavailable
    }
}

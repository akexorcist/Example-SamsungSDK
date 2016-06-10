package com.akexorcist.knoxmultiuserapp;

import android.app.enterprise.multiuser.MultiUserManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGeofencingStart;
    private Button btnGeofencingStop;
    private Button btnCreateGeofence;
    private Button btnClearGeofence;

    private MultiUserManager multiUserManager;

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
        MultiUserManager multiUserManager = MultiUserManager.getInstance(this);
        multiUserManager.multipleUsersAllowed();
        if (multiUserManager.multipleUsersSupported()) {
            // Call MultiUserManager method
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnGeofencingStart) {
//            startGeofencing();
        } else if (v == btnGeofencingStop) {
//            stopGeofencing();
        } else if (v == btnCreateGeofence) {
//            createGeofence();
        } else if (v == btnClearGeofence) {
//            clearGeofence();
        }
    }
}

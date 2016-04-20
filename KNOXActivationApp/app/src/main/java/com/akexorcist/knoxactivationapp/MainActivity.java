package com.akexorcist.knoxactivationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxactivator.KnoxActivationManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ActivationCallback {

    private Button btnActivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isDeviceAdminActivated = KnoxActivationManager.getInstance().isDeviceAdminActivated(this);
        btnActivate = (Button) findViewById(R.id.btn_activate);
        btnActivate.setOnClickListener(this);
        btnActivate.setEnabled(!isDeviceAdminActivated);
    }

    @Override
    public void onStart() {
        super.onStart();
        KnoxActivationManager.getInstance().register(this, this);
    }

    @Override
    public void onStop() {
        super.onStop();
        KnoxActivationManager.getInstance().unregister(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnActivate) {
            KnoxActivationManager.getInstance().activateDeviceAdmin(this);
        }
    }

    @Override
    public void onDeviceAdminActivated() {
        Log.e("Check", "onDeviceAdminActivated");
        btnActivate.setEnabled(false);
    }

    @Override
    public void onDeviceAdminDeactivated() {
        Log.e("Check", "onDeviceAdminDeactivated");
        btnActivate.setEnabled(true);
    }

    @Override
    public void onLicenseActivated() {
        Log.e("Check", "onLicenseActivated");
    }

    @Override
    public void onLicenseActivateFailed() {
        Log.e("Check", "onLicenseActivateFailed");
    }

    @Override
    public void onLicenseDeactivated() {
        Log.e("Check", "onLicenseDeactivated");
    }
}

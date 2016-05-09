package com.akexorcist.knoxactivationapp;

import android.app.enterprise.EnterpriseDeviceManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxactivator.KnoxActivationManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ActivationCallback {
    private String LICENSE_KEY = "YOUR_ELM_KEY";
    private Button btnActivateDeviceAdmin;
    private Button btnDeactivateDeviceAdmin;
    private Button btnLicense;
    private Button btnLockDeviceAdmin;
    private Button btnUnlockDeviceAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivateDeviceAdmin = (Button) findViewById(R.id.btn_activate_device_admin);
        btnDeactivateDeviceAdmin = (Button) findViewById(R.id.btn_deactivate_device_admin);
        btnLicense = (Button) findViewById(R.id.btn_license);
        btnLockDeviceAdmin = (Button) findViewById(R.id.btn_lock_device_admin);
        btnUnlockDeviceAdmin = (Button) findViewById(R.id.btn_unlock_device_admin);
        btnActivateDeviceAdmin.setOnClickListener(this);
        btnDeactivateDeviceAdmin.setOnClickListener(this);
        btnLicense.setOnClickListener(this);
        btnLockDeviceAdmin.setOnClickListener(this);
        btnUnlockDeviceAdmin.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        KnoxActivationManager.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        KnoxActivationManager.getInstance().unregister();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KnoxActivationManager.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if (v == btnActivateDeviceAdmin) {
            KnoxActivationManager.getInstance().activateDeviceAdmin(this);
        } else if (v == btnDeactivateDeviceAdmin) {
            KnoxActivationManager.getInstance().deactivateDeviceAdmin(this);
        } else if (v == btnLicense) {
            KnoxActivationManager.getInstance().activateLicense(this, LICENSE_KEY);
        } else if (v == btnLockDeviceAdmin) {
            lockDeviceAdmin();
        } else if (v == btnUnlockDeviceAdmin) {
            unlockDeviceAdmin();
        }
    }

    @Override
    public void onDeviceAdminActivated() {
        Toast.makeText(this, "Device Admin Activated.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeviceAdminActivationCancelled() {
        Toast.makeText(this, "Device Admin Activation Cancelled.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeviceAdminDeactivated() {
        Toast.makeText(this, "Device Admin Deactivated.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLicenseActivated() {
        Toast.makeText(this, "License Deactivated.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLicenseActivateFailed(int errorType, String errorMessage) {
        Toast.makeText(this, "License Activation Failed : " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void lockDeviceAdmin() {
        setAdminRemovable(false);
    }

    public void unlockDeviceAdmin() {
        setAdminRemovable(true);
    }

    @SuppressWarnings("WrongConstant")
    public void setAdminRemovable(boolean state) {
        if (KnoxActivationManager.getInstance().isDeviceAdminActivated(this)) {
            EnterpriseDeviceManager enterpriseDeviceManager = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
            enterpriseDeviceManager.setAdminRemovable(state);
        } else {
            Toast.makeText(this, "Please activate Device Admin and License before set device admin removable.", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.akexorcist.knoxactivationapp;

import android.app.enterprise.EnterpriseDeviceManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxactivator.KnoxActivationManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ActivationCallback {
    private String LICENSE_KEY = "C9BA38BFB9967E5FE515782500ED6EC36ABCF1EE0B3BC11A61141041E129F78C6F07AA79543581BD237CC5606DF14BC5D3F515EBE3BBCE99445B190F0973D8C0";
    private Button btnActivateDeviceAdmin;
    private Button btnDeactivateDeviceAdmin;
    private Button btnLicense;
    private Button btnLockDeviceAdmin;
    private Button btnUnlockDeviceAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isDeviceAdminActivated = KnoxActivationManager.getInstance().isDeviceAdminActivated(this);
//        boolean isLicenseActivated = KnoxActivationManager.getInstance().isLicenseActivated(this);
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
//        btnActivateDeviceAdmin.setEnabled(!isDeviceAdminActivated);
//        btnDeactivateDeviceAdmin.setEnabled(!isDeviceAdminActivated);
//        btnLicense.setEnabled(!isLicenseActivated);
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
    public void onClick(View v) {
        if (v == btnActivateDeviceAdmin) {
            KnoxActivationManager.getInstance().activateDeviceAdmin(this);
        } else if (v == btnDeactivateDeviceAdmin) {
            KnoxActivationManager.getInstance().deactivateDeviceAdmin(this);
        } else if (v == btnLicense) {
            KnoxActivationManager.getInstance().activateLicense(this, LICENSE_KEY);
        } else if (v == btnLockDeviceAdmin) {
            lockDeviceAdmin(true);
        } else if (v == btnUnlockDeviceAdmin) {
            lockDeviceAdmin(false);
        }
    }

    @Override
    public void onDeviceAdminActivated() {
        Log.e("Check", "onDeviceAdminActivated");
//        btnActivateDeviceAdmin.setEnabled(false);
    }

    @Override
    public void onDeviceAdminDeactivated() {
        Log.e("Check", "onDeviceAdminDeactivated");
//        btnActivateDeviceAdmin.setEnabled(true);
    }

    @Override
    public void onLicenseActivated() {
        Log.e("Check", "onLicenseActivated");
    }

    @Override
    public void onLicenseActivateFailed(int errorType, String errorMessage) {
        Log.e("Check", "onLicenseActivateFailed : " + errorMessage);

    }

    @Override
    public void onLicenseDeactivated() {
        Log.e("Check", "onLicenseDeactivated");
    }

    public void lockDeviceAdmin(boolean state) {
        EnterpriseDeviceManager enterpriseDeviceManager = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
        enterpriseDeviceManager.setAdminRemovable(state);
    }
}

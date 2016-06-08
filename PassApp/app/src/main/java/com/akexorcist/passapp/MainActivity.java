package com.akexorcist.passapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spass spass;
    private SpassFingerprint spassFingerprint;

    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVerify = (Button) findViewById(R.id.btn_verify);
        btnVerify.setOnClickListener(this);

        if (setupSPass()) {
            btnVerify.setEnabled(true);
        } else {
            btnVerify.setEnabled(false);
            Toast.makeText(this, "Device doesn't support Pass SDK.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean setupSPass() {
        spass = new Spass();
        try {
            spass.initialize(this);
            if (spass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT)) {
                spassFingerprint = new SpassFingerprint(this);
                return true;
            }
        } catch (SsdkUnsupportedException | UnsupportedOperationException e) {
            // Fingerprint Service is not supported in this SDK
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnVerify) {
            startIdentifyDialog();
        }
    }

    private void startIdentifyDialog() {
        if (spassFingerprint != null) {
            spassFingerprint.startIdentifyWithDialog(this, mIdentifyListenerDialog, false);
        }
    }

    private SpassFingerprint.IdentifyListener mIdentifyListenerDialog = new SpassFingerprint.IdentifyListener() {
        @Override
        public void onFinished(int eventStatus) {
            Toast.makeText(MainActivity.this, getEventStatusName(eventStatus), Toast.LENGTH_SHORT).show();
            if (eventStatus == SpassFingerprint.STATUS_AUTHENTIFICATION_SUCCESS) {
                // Authentication Success
                int index = spassFingerprint.getIdentifiedFingerprintIndex();
                Toast.makeText(MainActivity.this, "Fingerprint Index : " + index, Toast.LENGTH_SHORT).show();
                openInfoActivity();
            } else if (eventStatus == SpassFingerprint.STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS) {
                // Authentication Password Success
            } else if (eventStatus == SpassFingerprint.STATUS_USER_CANCELLED
                    || eventStatus == SpassFingerprint.STATUS_USER_CANCELLED_BY_TOUCH_OUTSIDE) {
                // Authentication Cancel
            } else if (eventStatus == SpassFingerprint.STATUS_TIMEOUT_FAILED) {
                // Authentication Timeout
            } else if (eventStatus == SpassFingerprint.STATUS_QUALITY_FAILED) {
                // Authentication Quality Failed
            } else {
                // Authentication Failed
            }
        }

        @Override
        public void onReady() {
            // Fingerprint sensor is ready
        }

        @Override
        public void onStarted() {
            // User touched fingerprint sensor
        }

        @Override
        public void onCompleted() {
            // Identify is completed
        }
    };

    private String getEventStatusName(int eventStatus) {
        switch (eventStatus) {
            case SpassFingerprint.STATUS_AUTHENTIFICATION_SUCCESS:
                return "STATUS_AUTHENTIFICATION_SUCCESS";
            case SpassFingerprint.STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS:
                return "STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS";
            case SpassFingerprint.STATUS_TIMEOUT_FAILED:
                return "STATUS_TIMEOUT";
            case SpassFingerprint.STATUS_SENSOR_FAILED:
                return "STATUS_SENSOR_ERROR";
            case SpassFingerprint.STATUS_USER_CANCELLED:
                return "STATUS_USER_CANCELLED";
            case SpassFingerprint.STATUS_QUALITY_FAILED:
                return "STATUS_QUALITY_FAILED";
            case SpassFingerprint.STATUS_USER_CANCELLED_BY_TOUCH_OUTSIDE:
                return "STATUS_USER_CANCELLED_BY_TOUCH_OUTSIDE";
            default:
                return "STATUS_AUTHENTIFICATION_FAILED";
        }
    }

    private void openInfoActivity() {
        startActivity(new Intent(this, InfoActivity.class));
    }
}

package com.akexorcist.passapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.samsung.android.sdk.pass.SpassFingerprint;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // TODO (1) : Declare Spass and SpassFingerprint instance

    private TextView tvLoginDescription;
    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        setup();
    }

    public void bindView() {
        tvLoginDescription = (TextView) findViewById(R.id.tv_login_description);
        btnVerify = (Button) findViewById(R.id.btn_verify);
    }

    public void setup() {
        btnVerify.setOnClickListener(this);

        if (setupSPass()) {
            btnVerify.setEnabled(true);
            tvLoginDescription.setText(R.string.sign_in_description);
        } else {
            btnVerify.setEnabled(false);
            tvLoginDescription.setText(R.string.device_does_not_supported_pass_sdk);
        }
    }

    private boolean setupSPass() {
        // TODO (2) : Initialize Pass SDK and return boolean result

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnVerify) {
            startIdentifyDialog();
        }
    }

    private void startIdentifyDialog() {
        // TODO (3) : Start fingerprint scanning by with default identify dialog
    }

    private SpassFingerprint.IdentifyListener mIdentifyListenerDialog = new SpassFingerprint.IdentifyListener() {
        @Override
        public void onFinished(int eventStatus) {
            // TODO (4) : Check result from fingerprint scanning. If status is success, open next activity with openInfoActivity();

        }

        @Override
        public void onReady() {
        }

        @Override
        public void onStarted() {
        }

        @Override
        public void onCompleted() {
        }
    };

    private void openInfoActivity() {
        startActivity(new Intent(this, InfoActivity.class));
    }
}

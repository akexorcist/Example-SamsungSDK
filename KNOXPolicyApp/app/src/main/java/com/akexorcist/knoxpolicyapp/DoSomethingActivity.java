package com.akexorcist.knoxpolicyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"ConstantConditions", "ArraysAsListWithZeroOrOneArgument", "SpellCheckingInspection"})
public class DoSomethingActivity extends AppCompatActivity {

    private Button btnFacebookPrevent;
    private Button btnFacebookClear;
    private Button btnCalculatorChange;
    private Button btnCalculatorClear;
    private Button btnYouTubeDisable;
    private Button btnYouTubeEnable;
    private Button btnDriveDisable;
    private Button btnDriveEnable;
    private Button btnGpsStart;
    private Button btnGpsStop;
    private Button btnCameraDisable;
    private Button btnCameraEnable;
    private Button btnHomeDisable;
    private Button btnHomeEnable;
    private Button btnShareDisallow;
    private Button btnShareAllow;
    private Button btnSettingsDisallow;
    private Button btnSettingsAllow;

    // TODO (1) : Declare application policy
    // TODO (2) : Declare location policy
    // TODO (3) : Declare restriction policy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);

        bindView();
        setupView();
        setupThing();
    }

    private void bindView() {
        btnFacebookPrevent = (Button) findViewById(R.id.btn_facebook_prevent);
        btnFacebookClear = (Button) findViewById(R.id.btn_facebook_clear);
        btnCalculatorChange = (Button) findViewById(R.id.btn_calculator_change);
        btnCalculatorClear = (Button) findViewById(R.id.btn_calculator_clear);
        btnYouTubeDisable = (Button) findViewById(R.id.btn_youtube_disable);
        btnYouTubeEnable = (Button) findViewById(R.id.btn_youtube_enable);
        btnDriveDisable = (Button) findViewById(R.id.btn_drive_disable);
        btnDriveEnable = (Button) findViewById(R.id.btn_drive_enable);
        btnGpsStart = (Button) findViewById(R.id.btn_gps_start);
        btnGpsStop = (Button) findViewById(R.id.btn_gps_stop);
        btnCameraDisable = (Button) findViewById(R.id.btn_camera_disable);
        btnCameraEnable = (Button) findViewById(R.id.btn_camera_enable);
        btnHomeDisable = (Button) findViewById(R.id.btn_home_disable);
        btnHomeEnable = (Button) findViewById(R.id.btn_home_enable);
        btnShareDisallow = (Button) findViewById(R.id.btn_share_disallow);
        btnShareAllow = (Button) findViewById(R.id.btn_share_allow);
        btnSettingsDisallow = (Button) findViewById(R.id.btn_settings_disallow);
        btnSettingsAllow = (Button) findViewById(R.id.btn_settings_allow);
    }

    private void setupView() {
        btnFacebookPrevent.setOnClickListener(facebookClickListener);
        btnFacebookClear.setOnClickListener(facebookClickListener);
        btnCalculatorChange.setOnClickListener(calculatorClickListener);
        btnCalculatorClear.setOnClickListener(calculatorClickListener);
        btnYouTubeDisable.setOnClickListener(youtubeClickListener);
        btnYouTubeEnable.setOnClickListener(youtubeClickListener);
        btnDriveDisable.setOnClickListener(driveClickListener);
        btnDriveEnable.setOnClickListener(driveClickListener);
        btnGpsStart.setOnClickListener(gpsClickListener);
        btnGpsStop.setOnClickListener(gpsClickListener);
        btnCameraDisable.setOnClickListener(cameraClickListener);
        btnCameraEnable.setOnClickListener(cameraClickListener);
        btnHomeDisable.setOnClickListener(homeClickListener);
        btnHomeEnable.setOnClickListener(homeClickListener);
        btnShareDisallow.setOnClickListener(shareClickListener);
        btnShareAllow.setOnClickListener(shareClickListener);
        btnSettingsDisallow.setOnClickListener(settingsClickListener);
        btnSettingsAllow.setOnClickListener(settingsClickListener);
    }

    @SuppressWarnings("WrongConstant")
    private void setupThing() {
        // TODO (4) : Create application policy instance
        // TODO (5) : Create location policy instance
        // TODO (6) : Create restriction policy instance

    }

    private View.OnClickListener facebookClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List<String> appList = Arrays.asList("com.facebook.katana");
            if (v == btnFacebookPrevent) {
                // TODO (7) : Prevent Facebook app to start by add package name into blacklist

            } else if (v == btnFacebookClear) {
                // TODO (8) : Clear Facebook app to start by remove package name from blacklist

            }
        }
    };

    private View.OnClickListener calculatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String calculatorPackage = "com.sec.android.app.popupcalculator";
            if (v == btnCalculatorChange) {
                // TODO (9) : Change Calculator app name to another app name

            } else if (v == btnCalculatorClear) {
                // TODO (10) : Restore Calculator app name

            }
        }
    };

    private View.OnClickListener youtubeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnYouTubeDisable) {
                // TODO (11) : Disable YouTube app

            } else if (v == btnYouTubeEnable) {
                // TODO (12) : Enable YouTube app

            }
        }
    };

    private View.OnClickListener driveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String drivePackage = "com.google.android.apps.docs";
            if (v == btnDriveDisable) {
                // TODO (13) : Disable Google Drive app

            } else if (v == btnDriveEnable) {
                // TODO (14) : Enaable Google Drive app

            }
        }
    };

    private View.OnClickListener gpsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnGpsStart) {
                // TODO (15) : Start GPS

            } else if (v == btnGpsStop) {
                // TODO (16) : Stop GPS

            }
        }
    };

    private View.OnClickListener cameraClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnCameraDisable) {
                // TODO (17) : Disable camera

            } else if (v == btnCameraEnable) {
                // TODO (18) : Enable camera

            }
        }
    };

    private View.OnClickListener homeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnHomeDisable) {
                // TODO (19) : Disable home key

            } else if (v == btnHomeEnable) {
                // TODO (20) : Enable home key

            }
        }
    };

    private View.OnClickListener shareClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnShareDisallow) {
                // TODO (21) : Disable share list

            } else if (v == btnShareAllow) {
                // TODO (22) : Enable share list

            }
        }
    };

    private View.OnClickListener settingsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnSettingsDisallow) {
                // TODO (23) : Disallow settings changes

            } else if (v == btnSettingsAllow) {
                // TODO (24) : Allow settings changes

            }
        }
    };
}

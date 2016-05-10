package com.akexorcist.knoxpolicyapp;

import android.app.enterprise.ApplicationPolicy;
import android.app.enterprise.EnterpriseDeviceManager;
import android.app.enterprise.LocationPolicy;
import android.app.enterprise.RestrictionPolicy;
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

    private EnterpriseDeviceManager edm;
    private ApplicationPolicy appPolicy;
    private LocationPolicy locationPolicy;
    private RestrictionPolicy restrictionPolicy;

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
        edm = (EnterpriseDeviceManager) getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
        appPolicy = edm.getApplicationPolicy();
        locationPolicy = edm.getLocationPolicy();
        restrictionPolicy = edm.getRestrictionPolicy();
    }

    private View.OnClickListener facebookClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List<String> appList = Arrays.asList("com.facebook.katana");
            if (v == btnFacebookPrevent) {
                appPolicy.addPackagesToPreventStartBlackList(appList);
            } else if (v == btnFacebookClear) {
                appPolicy.removePackagesFromPreventStartBlackList(appList);
            }
        }
    };

    private View.OnClickListener calculatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String calculatorPackage = "com.sec.android.app.popupcalculator";
            if (v == btnCalculatorChange) {
                appPolicy.changeApplicationName(calculatorPackage, "iTune");
            } else if (v == btnCalculatorClear) {
                appPolicy.changeApplicationName(calculatorPackage, "Calculator");
            }
        }
    };

    private View.OnClickListener youtubeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnYouTubeDisable) {
                appPolicy.disableYouTube();
            } else if (v == btnYouTubeEnable) {
                appPolicy.enableYouTube();
            }
        }
    };

    private View.OnClickListener driveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String drivePackage = "com.google.android.apps.docs";
            if (v == btnDriveDisable) {
                appPolicy.setDisableApplication(drivePackage);
            } else if (v == btnDriveEnable) {
                appPolicy.setEnableApplication(drivePackage);
            }
        }
    };

    private View.OnClickListener gpsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnGpsStart) {
                locationPolicy.startGPS(true);
            } else if (v == btnGpsStop) {
                locationPolicy.startGPS(false);
            }
        }
    };

    private View.OnClickListener cameraClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnCameraDisable) {
                restrictionPolicy.setCameraState(false);
            } else if (v == btnCameraEnable) {
                restrictionPolicy.setCameraState(true);
            }
        }
    };

    private View.OnClickListener homeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnHomeDisable) {
                restrictionPolicy.setHomeKeyState(false);
            } else if (v == btnHomeEnable) {
                restrictionPolicy.setHomeKeyState(true);
            }
        }
    };

    private View.OnClickListener shareClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnShareDisallow) {
                restrictionPolicy.allowShareList(false);
            } else if (v == btnShareAllow) {
                restrictionPolicy.allowShareList(true);
            }
        }
    };

    private View.OnClickListener settingsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnSettingsDisallow) {
                restrictionPolicy.allowSettingsChanges(false);
            } else if (v == btnSettingsAllow) {
                restrictionPolicy.allowSettingsChanges(true);
            }
        }
    };
}

package com.akexorcist.knoxsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxsampleapp.manager.DialogManager;
import com.akexorcist.knoxsampleapp.manager.SharedPreferenceManager;
import com.akexorcist.knoxsampleapp.manager.ToastManager;

public class MainActivity extends AppCompatActivity implements ActivationCallback {
    private final String LICENSE_KEY = "YOUR_KEY";

    private MaterialDialog dialogLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkDeviceAdminActivation();
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO Register KnoxActivationManager

    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO Unregister KnoxActivationManager

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO Call onActivityResult from KnoxActivationManager

    }

    @Override
    public void onDeviceAdminActivated() {
        showDeviceAdminActivationSuccess();
        activateKnoxLicense();
    }

    @Override
    public void onDeviceAdminActivationCancelled() {
        showDeviceAdminActivationProblem();
    }

    @Override
    public void onDeviceAdminDeactivated() {
    }

    @Override
    public void onLicenseActivated() {
        hideLoadingDialog();
        saveLicenseActivationStateToSharedPreference();
        showLicenseActivationSuccess();
        goToRestrictionActivity();
    }

    @Override
    public void onLicenseActivateFailed(int errorType, String errorMessage) {
        hideLoadingDialog();
        showLicenseActivationProblem(errorType, errorMessage);
    }

    private void checkDeviceAdminActivation() {
        // TODO Check KNOX SDK support with KnoxActivationManager
        // TODO If KNOX SDK is support for this device, then call activateDeviceAdmin
        if (true) {
            activateDeviceAdmin();
        } else {
            showDeviceUnsupportedProblem();
        }
    }

    private void activateDeviceAdmin() {
        // TODO Check Device Admin is enable with KnoxActivationManager
        // TODO If Device Admin isn't enable yet, then call activateDeviceAdmin from KnoxActivationManager
        if (true) {
            // TODO Call activateDeviceAdmin method from KnoxActivationManager

        } else {
            onDeviceAdminActivated();
        }
    }

    private void activateKnoxLicense() {
        if (!SharedPreferenceManager.isLicenseActivated(this)) {
            showLoadingDialog();
            // TODO Call activateLicense method from KnoxActivationManager for license activation

        } else {
            showLicenseActivationSuccess();
            goToRestrictionActivity();
        }
    }

    private void saveLicenseActivationStateToSharedPreference() {
        SharedPreferenceManager.setLicenseActivated(this);
    }

    private void goToRestrictionActivity() {
        startActivity(new Intent(this, DoSomethingActivity.class));
        finish();
    }

    private void showLicenseActivationSuccess() {
        ToastManager.showLicenseActivationSuccess(this);
    }

    private void showDeviceAdminActivationSuccess() {
        ToastManager.showDeviceAdminActivationSuccess(this);
    }

    private void showDeviceUnsupportedProblem() {
        DialogManager.showDeviceUnsupportedProblem(this, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                finish();
            }
        });
    }

    private void showDeviceAdminActivationProblem() {
        DialogManager.showDeviceAdminActivationProblem(this, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (which == DialogAction.POSITIVE) {
                    activateDeviceAdmin();
                } else if (which == DialogAction.NEGATIVE) {
                    finish();
                }
            }
        });
    }

    private void showLicenseActivationProblem(int errorType, String errorMessage) {
        DialogManager.showLicenseActivationProblem(this, errorType, errorMessage, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (which == DialogAction.POSITIVE) {
                    activateKnoxLicense();
                } else if (which == DialogAction.NEGATIVE) {
                    finish();
                }
            }
        });
    }

    private void showLoadingDialog() {
        dialogLoading = DialogManager.showLicenseActivationLoading(this);
    }

    private void hideLoadingDialog() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
            dialogLoading = null;
        }
    }
}

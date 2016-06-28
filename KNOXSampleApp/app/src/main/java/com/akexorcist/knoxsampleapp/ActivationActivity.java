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

public class ActivationActivity extends AppCompatActivity {
    private final String LICENSE_KEY = "YOUR_ELM_KEY";

    private MaterialDialog dialogLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
        checkKnoxSdkSupported();
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO (1) : Register KnoxActivationManager with activationCallback

    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO (2) : Unregister KnoxActivationManager

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO (3) : Call onActivityResult from KnoxActivationManager

    }

    private ActivationCallback activationCallback = new ActivationCallback() {
        @Override
        public void onDeviceAdminActivated() {
            setDeviceAdminActivated();
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
    };

    private void checkKnoxSdkSupported() {
        // TODO (4) : Check KNOX SDK support with KnoxActivationManager. If supported, call activateDeviceAdmin()
        if (false) {
            activateDeviceAdmin();
        } else {
            showDeviceUnsupportedProblem();
        }
    }

    private void activateDeviceAdmin() {
        // TODO (5) : Check Device Admin is enable with KnoxActivationManager. If disabled, call activateDeviceAdmin()
        if (false) {
            setDeviceAdminActivated();
        } else {
            activateDeviceAdmin();
        }
    }

    private void activateKnoxLicense() {
        if (SharedPreferenceManager.isLicenseActivated(this)) {
            showLicenseActivationSuccess();
            goToRestrictionActivity();
        } else {
            showLoadingDialog();
            // TODO (6) : Call activateLicense method from KnoxActivationManager for license activation

        }
    }

    private void setDeviceAdminActivated() {
        showDeviceAdminActivationSuccess();
        activateKnoxLicense();
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

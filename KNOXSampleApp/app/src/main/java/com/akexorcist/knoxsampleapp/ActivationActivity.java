package com.akexorcist.knoxsampleapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxactivator.KnoxActivationManager;
import com.akexorcist.knoxsampleapp.manager.DialogManager;
import com.akexorcist.knoxsampleapp.manager.SharedPreferenceManager;
import com.akexorcist.knoxsampleapp.manager.ToastManager;

public class ActivationActivity extends AppCompatActivity {
    private final String LICENSE_KEY = "YOUR_ELM_KEY";

    private Dialog dialogLoading;

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
        KnoxActivationManager.getInstance().register(activationCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO (2) : Unregister KnoxActivationManager
        KnoxActivationManager.getInstance().unregister();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO (3) : Call onActivityResult from KnoxActivationManager
        KnoxActivationManager.getInstance().onActivityResult(requestCode, resultCode, data);
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
        if (KnoxActivationManager.getInstance().isKnoxSdkSupported(this)) {
            activateDeviceAdmin();
        } else {
            showDeviceUnsupportedProblem();
        }
    }

    private void activateDeviceAdmin() {
        // TODO (5) : Check Device Admin is enable with KnoxActivationManager. If disabled, call activateDeviceAdmin()
        if (KnoxActivationManager.getInstance().isDeviceAdminActivated(this)) {
            setDeviceAdminActivated();
        } else {
            // TODO (6) : Call activateDeviceAdmin method from KnoxActivationManager for device admin activation
            KnoxActivationManager.getInstance().activateDeviceAdmin(this);
        }
    }

    private void activateKnoxLicense() {
        if (SharedPreferenceManager.isLicenseActivated(this)) {
            showLicenseActivationSuccess();
            goToRestrictionActivity();
        } else {
            showLoadingDialog();
            // TODO (7) : Call activateLicense method from KnoxActivationManager for license activation
            KnoxActivationManager.getInstance().activateLicense(this, LICENSE_KEY);

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
        DialogManager.showDeviceUnsupportedProblem(this, new DialogManager.OnDialogClickAdapter() {
            @Override
            public void onNeutralClick() {
                finish();
            }
        });
    }

    private void showDeviceAdminActivationProblem() {
        DialogManager.showDeviceAdminActivationProblem(this, new DialogManager.OnDialogClickAdapter() {
            @Override
            public void onPositiveClick() {
                activateDeviceAdmin();
            }

            @Override
            public void onNegativeClick() {
                finish();
            }
        });
    }

    private void showLicenseActivationProblem(int errorType, String errorMessage) {
        DialogManager.showLicenseActivationProblem(this, errorType, errorMessage, new DialogManager.OnDialogClickAdapter() {
            @Override
            public void onPositiveClick() {
                activateKnoxLicense();
            }

            @Override
            public void onNegativeClick() {
                finish();
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

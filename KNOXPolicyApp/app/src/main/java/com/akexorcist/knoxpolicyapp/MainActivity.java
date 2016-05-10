package com.akexorcist.knoxpolicyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxactivator.KnoxActivationManager;
import com.akexorcist.knoxpolicyapp.manager.DialogManager;
import com.akexorcist.knoxpolicyapp.manager.ToastManager;

public class MainActivity extends AppCompatActivity implements ActivationCallback {
    private String LICENSE_KEY = "ADF98C412CE988272A5559422AA36823D4D60127012F50BBCFE3DFCFD43BB9CD27FA8EF2BB22F98BBD8CD17775638E61DC12E8474C66C8A5F5DF1405A9B2DA49";

    private MaterialDialog dialogLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkDeviceAdminActivation();
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
        showLicenseActivationSuccess();
        goToRestrictionActivity();
    }

    @Override
    public void onLicenseActivateFailed(int errorType, String errorMessage) {
        hideLoadingDialog();
        showLicenseActivationProblem(errorType, errorMessage);
    }

    public void checkDeviceAdminActivation() {
        if (KnoxActivationManager.getInstance().isKnoxSdkSupported(this)) {
            activateDeviceAdmin();
        } else {
            showDeviceUnsupportedProblem();
        }
    }

    public void activateDeviceAdmin() {
        if (!KnoxActivationManager.getInstance().isDeviceAdminActivated(this)) {
            KnoxActivationManager.getInstance().activateDeviceAdmin(this);
        } else {
            onDeviceAdminActivated();
        }
    }

    public void activateKnoxLicense() {
        showLoadingDialog();
        KnoxActivationManager.getInstance().activateLicense(this, LICENSE_KEY);
    }

    public void goToRestrictionActivity() {
        startActivity(new Intent(this, DoSomethingActivity.class));
        finish();
    }

    public void showLicenseActivationSuccess() {
        ToastManager.showLicenseActivationSuccess(this);
    }

    public void showDeviceAdminActivationSuccess() {
        ToastManager.showDeviceAdminActivationSuccess(this);
    }

    public void showDeviceUnsupportedProblem() {
        DialogManager.showDeviceUnsupportedProblem(this, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                finish();
            }
        });
    }

    public void showDeviceAdminActivationProblem() {
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

    public void showLicenseActivationProblem(int errorType, String errorMessage) {
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

    public void showLoadingDialog() {
        dialogLoading = DialogManager.showLicenseActivationLoading(this);
    }

    public void hideLoadingDialog() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
            dialogLoading = null;
        }
    }
}

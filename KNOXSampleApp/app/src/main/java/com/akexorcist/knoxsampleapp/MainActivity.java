package com.akexorcist.knoxsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.knoxactivator.ActivationCallback;
import com.akexorcist.knoxactivator.KnoxActivationManager;
import com.akexorcist.knoxsampleapp.manager.DialogManager;
import com.akexorcist.knoxsampleapp.manager.ToastManager;

public class MainActivity extends AppCompatActivity implements ActivationCallback {
    private String LICENSE_KEY = "C9BA38BFB9967E5FE515782500ED6EC36ABCF1EE0B3BC11A61141041E129F78C6F07AA79543581BD237CC5606DF14BC5D3F515EBE3BBCE99445B190F0973D8C0";

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

        Log.e("Check", "onStart");
        KnoxActivationManager.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Check", "onStop");
        KnoxActivationManager.getInstance().unregister();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KnoxActivationManager.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDeviceAdminActivated() {
        Log.e("Check", "onDeviceAdminActivated");
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
            Log.e("Check", "activateDeviceAdmin");
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
                Log.e("Check", "OnClick");
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

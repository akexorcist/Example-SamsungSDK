package com.akexorcist.knoxkioskapp.manager;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.knoxkioskapp.R;

/**
 * Created by Akexorcist on 4/22/2016 AD.
 */
public class DialogManager {
    public static MaterialDialog showLicenseActivationLoading(Context context) {
        return new MaterialDialog.Builder(context)
                .title(R.string.license_activation_loading_title)
                .content(R.string.license_activation_loading_content)
                .progress(true, 0)
                .show();
    }

    public static void showDialog(Context context, String title, String content, String positive, String negative, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(positive)
                .negativeText(negative)
                .onNegative(callback)
                .onPositive(callback)
                .show();
    }

    public static void showDeviceUnsupportedProblem(Context context, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(context)
                .title(R.string.device_unsupported_title)
                .content(R.string.device_unsupported_content)
                .positiveText(R.string.ok)
                .onPositive(callback)
                .show();
    }

    public static void showDeviceAdminActivationProblem(Context context, MaterialDialog.SingleButtonCallback callback) {
        showDialog(context,
                context.getString(R.string.device_admin_cancelled_title),
                context.getString(R.string.device_admin_cancelled_content),
                context.getString(R.string.retry),
                context.getString(R.string.cancel),
                callback);
    }

    public static void showLicenseActivationProblem(Context context, int errorType, String errorMessage, MaterialDialog.SingleButtonCallback callback) {
        showDialog(context,
                context.getString(R.string.license_failed_title),
                context.getString(R.string.license_failed_content, errorMessage, errorType),
                context.getString(R.string.retry),
                context.getString(R.string.cancel),
                callback);
    }
}

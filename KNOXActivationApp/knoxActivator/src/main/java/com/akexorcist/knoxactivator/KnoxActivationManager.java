package com.akexorcist.knoxactivator;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.app.enterprise.EnterpriseDeviceManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by Akexorcist on 4/20/2016 AD.
 */
public class KnoxActivationManager implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final int REQUEST_CODE_KNOX_ACTIVATION = 79;
    private static KnoxActivationManager knoxActivationManager;

    public static KnoxActivationManager getInstance() {
        if (knoxActivationManager == null) {
            knoxActivationManager = new KnoxActivationManager();
        }
        return knoxActivationManager;
    }

    //    private HeadsetCallback headsetCallback;
    private ActivationCallback activationCallback;

    public void register(Context context, ActivationCallback callback) {
        this.activationCallback = callback;
        KnoxActivationPreference.getInstance().setSharedPreferenceChangeListener(context, this);
        checkDeviceAdminActivation(context);
    }

    public void unregister(Context context) {
        KnoxActivationPreference.getInstance().clearSharedPreferenceChangeListener(context, this);
    }

    private void checkDeviceAdminActivation(Context context) {
        if(activationCallback != null) {
            if (KnoxActivationPreference.getInstance().getDeviceAdminActivationState(context)) {
                activationCallback.onDeviceAdminActivated();
            } else {
                activationCallback.onDeviceAdminDeactivated();
            }
        }
    }

    public boolean isDeviceAdminActivated(Context context) {
        return KnoxActivationPreference.getInstance().getDeviceAdminActivationState(context);
    }

//    private SimpleBroadcastReceiver simpleBroadcastReceiver = new SimpleBroadcastReceiver() {
//        @Override
//        public void onHeadsetPlugged(Context context) {
//            if (!KnoxActivationPreference.getInstance().getDeviceAdminActivationState(context)) {
//                KnoxActivationPreference.getInstance().setDeviceAdminActivate(context, true);
//                if (headsetCallback != null) {
//                    headsetCallback.onHeadsetPlugged();
//                }
//            }
//        }
//
//        @Override
//        public void onHeadsetUnplugged(Context context) {
//            if (KnoxActivationPreference.getInstance().getDeviceAdminActivationState(context)) {
//                KnoxActivationPreference.getInstance().setDeviceAdminActivate(context, false);
//                if (headsetCallback != null) {
//                    headsetCallback.onHeadsetUnplugged();
//                }
//            }
//        }
//    };

    private void setDeviceActivated() {
        if (activationCallback != null) {
            activationCallback.onDeviceAdminActivated();
        }
    }

    private void setDeviceDeactivated() {
        if (activationCallback != null) {
            activationCallback.onDeviceAdminDeactivated();
        }
    }

    @SuppressWarnings("WrongConstant")
    public boolean isMdmApiSupported(Context context, MyConstant.MDMVersion requiredVersion) {
        EnterpriseDeviceManager mEDM = (EnterpriseDeviceManager) context.getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
        return !(requiredVersion != null && mEDM.getEnterpriseSdkVer().ordinal() < requiredVersion.ordinal());
    }

    public void activateDeviceAdmin(Activity activity) {
        ComponentName componentName = new ComponentName(activity, AdminActivationReceiver.class);
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        activity.startActivityForResult(intent, REQUEST_CODE_KNOX_ACTIVATION);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equalsIgnoreCase(KnoxActivationPreference.KEY_DEVICE_ADMIN_STATE)) {
            if (sharedPreferences.getBoolean(KnoxActivationPreference.KEY_DEVICE_ADMIN_STATE, false)) {
                setDeviceActivated();
            } else {
                setDeviceDeactivated();
            }
        }
    }
}

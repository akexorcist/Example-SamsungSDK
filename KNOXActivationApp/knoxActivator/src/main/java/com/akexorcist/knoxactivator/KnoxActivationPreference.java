package com.akexorcist.knoxactivator;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Akexorcist on 4/20/2016 AD.
 */
public class KnoxActivationPreference {
    public static final String PREFERENCE_KNOX = "knox_preference";
    public static final String KEY_DEVICE_ADMIN_STATE = "device_admin_state";
    public static final String KEY_LICENSE_STATE = "license_state";

    private static KnoxActivationPreference knoxActivationPreference;

    public static KnoxActivationPreference getInstance() {
        if (knoxActivationPreference == null) {
            knoxActivationPreference = new KnoxActivationPreference();
        }
        return knoxActivationPreference;
    }

    public KnoxActivationPreference() {
    }

    public SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_KNOX, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor(Context context) {
        return getPreference(context).edit();
    }

    public void setDeviceAdminActivate(Context context, boolean state) {
        getEditor(context).putBoolean(KEY_DEVICE_ADMIN_STATE, state).apply();
    }

    public void setLicenseActivate(Context context, boolean state) {
        getEditor(context).putBoolean(KEY_LICENSE_STATE, state).apply();
    }

    public boolean getDeviceAdminActivationState(Context context) {
        return getPreference(context).getBoolean(KEY_DEVICE_ADMIN_STATE, false);
    }

    public boolean getLicenseActivationState(Context context) {
        return getPreference(context).getBoolean(KEY_LICENSE_STATE, false);
    }

    public void setSharedPreferenceChangeListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreference(context).registerOnSharedPreferenceChangeListener(listener);
    }

    public void clearSharedPreferenceChangeListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreference(context).unregisterOnSharedPreferenceChangeListener(listener);
    }
}

package com.akexorcist.knoxkioskapp;

import android.app.enterprise.kioskmode.KioskMode;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Akexorcist on 5/11/16 AD.
 */
public class KIOSKReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals(KioskMode.ACTION_ENABLE_KIOSK_MODE_RESULT)) {
                // TODO (3) Add application shortcut when kiosk mode enabled

            } else if (action.equals(KioskMode.ACTION_DISABLE_KIOSK_MODE_RESULT)) {
                // TODO (4) Remove application shortcut when kiosk mode disable

            } else if (action.equals(KioskMode.ACTION_UNEXPECTED_KIOSK_BEHAVIOR)) {
                // TODO (5) Do something when some error occurred
            }
        }
    }

    @SuppressWarnings("WrongConstant")
    void addApplicationShortcut(Context context, String packageName) {
        // TODO (1) Add this application shortcut to kiosk mode launcher
    }

    @SuppressWarnings("WrongConstant")
    void removeApplicationShortcut(Context context, String packageName) {
        // TODO (2) Remove this application shortcut to kiosk mode launcher
    }
}


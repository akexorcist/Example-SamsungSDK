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
                // KIOSK enabled
                addApplicationShortcut(context);
            } else if (action.equals(KioskMode.ACTION_DISABLE_KIOSK_MODE_RESULT)) {
                // KIOSK disabled
                removeApplicationShortcut(context);
            } else if (action.equals(KioskMode.ACTION_UNEXPECTED_KIOSK_BEHAVIOR)) {
                // KIOSK unexpected error
            }
        }
    }

    @SuppressWarnings("WrongConstant")
    void addApplicationShortcut(Context context) {
        // TODO Add this application shortcut to kiosk mode launcher
    }

    @SuppressWarnings("WrongConstant")
    void removeApplicationShortcut(Context context) {
        // TODO Remove this application shortcut to kiosk mode launcher
    }
}


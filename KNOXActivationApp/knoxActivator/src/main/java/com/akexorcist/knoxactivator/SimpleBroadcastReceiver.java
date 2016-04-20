package com.akexorcist.knoxactivator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by Akexorcist on 4/20/2016 AD.
 */
public class SimpleBroadcastReceiver extends BroadcastReceiver {
    public static final String EXTRA_KEY_ADDRESS = "address";
    public static final String EXTRA_KEY_STATE = "state";
    public static final String EXTRA_KEY_PORT_NAME = "portName";
    public static final String EXTRA_KEY_MICROPHONE = "microphone";

    public static IntentFilter createIntentFilter() {
        return new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            if (intent.getIntExtra(EXTRA_KEY_STATE, 0) == 0) {
                onHeadsetUnplugged(context);
            } else {
                onHeadsetPlugged(context);
            }
        }
    }

    public void onHeadsetPlugged(Context context) {
    }

    public void onHeadsetUnplugged(Context context) {
    }
}

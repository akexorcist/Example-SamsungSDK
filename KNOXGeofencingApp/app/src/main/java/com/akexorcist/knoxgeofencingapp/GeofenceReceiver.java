package com.akexorcist.knoxgeofencingapp;

import android.app.enterprise.geofencing.Geofencing;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Akexorcist on 5/10/16 AD.
 */
public class GeofenceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_INSIDE_GEOFENCE)) {
            Log.e("Check", "Inside Geofencing");
        } else if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_OUTSIDE_GEOFENCE)) {
            Log.e("Check", "Outside Geofencing");
        } else if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_LOCATION_UNAVAILABLE)) {
            Log.e("Check", "Location Unavailable");
        } else {
            Log.e("Check", "Receive Something");
        }
    }
}

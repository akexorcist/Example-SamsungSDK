package com.akexorcist.knoxgeofencingapp;

import android.app.enterprise.geofencing.Geofencing;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Akexorcist on 5/10/16 AD.
 */
public class GeofencingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_INSIDE_GEOFENCE)) {
            // Do something when device inside geofence
        } else if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_OUTSIDE_GEOFENCE)) {
            // Do something when device outside geofence
        } else if (action.equalsIgnoreCase(Geofencing.ACTION_DEVICE_LOCATION_UNAVAILABLE)) {
            // Do something when device location unavailable
        }
    }
}

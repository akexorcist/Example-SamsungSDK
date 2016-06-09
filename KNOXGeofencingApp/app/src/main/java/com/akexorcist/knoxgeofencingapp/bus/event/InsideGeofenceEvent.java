package com.akexorcist.knoxgeofencingapp.bus.event;

/**
 * Created by Akexorcist on 6/10/16 AD.
 */
public class InsideGeofenceEvent {
    int[] geofenceIdList;

    public InsideGeofenceEvent() {
    }

    public InsideGeofenceEvent(int[] geofenceIdList) {
        this.geofenceIdList = geofenceIdList;
    }

    public int[] getGeofenceIdList() {
        return geofenceIdList;
    }

    public void setGeofenceIdList(int[] geofenceIdList) {
        this.geofenceIdList = geofenceIdList;
    }
}

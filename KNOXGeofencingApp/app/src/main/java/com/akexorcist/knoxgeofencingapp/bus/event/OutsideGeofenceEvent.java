package com.akexorcist.knoxgeofencingapp.bus.event;

/**
 * Created by Akexorcist on 6/10/16 AD.
 */
public class OutsideGeofenceEvent {
    int[] geofenceIdList;

    public OutsideGeofenceEvent() {
    }

    public OutsideGeofenceEvent(int[] geofenceIdList) {
        this.geofenceIdList = geofenceIdList;
    }

    public int[] getGeofenceIdList() {
        return geofenceIdList;
    }

    public void setGeofenceIdList(int[] geofenceIdList) {
        this.geofenceIdList = geofenceIdList;
    }
}

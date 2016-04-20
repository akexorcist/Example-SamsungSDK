package com.akexorcist.knoxactivator;

/**
 * Created by Akexorcist on 4/20/2016 AD.
 */
public interface ActivationCallback {
    void onDeviceAdminActivated();

    void onDeviceAdminDeactivated();

    void onLicenseActivated();

    void onLicenseActivateFailed();

    void onLicenseDeactivated();
}

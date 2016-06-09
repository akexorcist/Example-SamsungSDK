package com.akexorcist.knoxgeofencingapp.bus;

import com.squareup.otto.Bus;

/**
 * Created by Akexorcist on 6/10/16 AD.
 */
public class BusProvider {
    private static Bus bus;

    public static Bus getProvider() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }
}

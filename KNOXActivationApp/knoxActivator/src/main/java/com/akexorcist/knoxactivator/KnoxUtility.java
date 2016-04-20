package com.akexorcist.knoxactivator;

/**
 * Created by Akexorcist on 4/20/2016 AD.
 */
public class KnoxUtility {
    private static KnoxUtility knoxUtility;

    public static KnoxUtility getInstance() {
        if (knoxUtility == null) {
            knoxUtility = new KnoxUtility();
        }
        return knoxUtility;
    }

}

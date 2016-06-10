package com.akexorcist.knoxmultiuserapp;

import android.app.enterprise.multiuser.MultiUserManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DoSomethingActivity extends AppCompatActivity{
    private MultiUserManager multiUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);

        bindView();
        setupView();
        setupThing();
    }

    private void bindView() {

    }

    private void setupView() {

    }

    private void setupThing() {
        MultiUserManager multiUserManager = MultiUserManager.getInstance(this);
        if (multiUserManager.multipleUsersSupported()) {
            // Call MultipleUserManager method
        }
    }
}

package com.akexorcist.knoxlsoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.akexorcist.knoxlsoapp.manager.FileAssetManager;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private String logoFileName = "akexorcist_logo.png";
    private Button btnSetCustomLSO;
    private Button btnClearCustomLSO;

    // TODO (1) : Declare lockscreen overlay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_something);

        bindView();
        setupView();
        setupThing();
    }

    private void bindView() {
        btnSetCustomLSO = (Button) findViewById(R.id.btn_set_custom_lso);
        btnClearCustomLSO = (Button) findViewById(R.id.btn_clear_custom_lso);
    }

    private void setupView() {
        btnSetCustomLSO.setOnClickListener(this);
        btnClearCustomLSO.setOnClickListener(this);
    }

    private void setupThing() {
        FileAssetManager.copyFileFromAssetToStorage(this, logoFileName, getFilesDir().getAbsolutePath() + logoFileName);
        // TODO (2) : Create lockscreen overlay instance

    }

    @Override
    public void onClick(View v) {
        if (v == btnSetCustomLSO) {
            setCustomLSO();
        } else if (v == btnClearCustomLSO) {
            clearCustomLSO();
        }
    }

    private void setCustomLSO() {
        // TODO (3) : Set custom lockscreen overlay

    }

    private void clearCustomLSO() {
        // TODO (4) : Clear all custom lockscreen overlay

    }
}

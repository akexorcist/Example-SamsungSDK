package com.akexorcist.knoxlsoapp;

import android.app.enterprise.lso.LockscreenOverlay;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.akexorcist.knoxlsoapp.manager.FileAssetManager;

public class DoSomethingActivity extends AppCompatActivity implements View.OnClickListener {
    private String logoFileName = "akexorcist_logo.png";
    private Button btnSetCustomLSO;
    private Button btnClearCustomLSO;

    private LockscreenOverlay lso;

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
        lso = LockscreenOverlay.getInstance(this);
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
        String name = "Sleeping For Less";
        String logo = getFilesDir().getAbsolutePath() + logoFileName;
        String address = "กรุงเทพมหานคร อมรรัตนโกสินทร์ มหินทรายุทธยา มหาดิลกภพ นพรัตนราชธานีบุรีรมย์...";
        String phone = "0987654321";
        lso.configure(name, logo, address, phone);
    }

    private void clearCustomLSO() {
        lso.resetAll();
    }
}

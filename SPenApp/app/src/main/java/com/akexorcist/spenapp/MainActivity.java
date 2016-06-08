package com.akexorcist.spenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout layoutDrawingWorkspace;
    private FrameLayout layoutPenSetting;
    private RelativeLayout layoutPenCanvas;
    private Button btnSave;
    private ImageButton btnPenSetting;

    // TODO Declare Spen, SpenNoteDoc, SpenPageDoc, SpenSimpleSurfaceView and SpenSettingPenLayout instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        setup();
    }

    private void bindView() {
        layoutDrawingWorkspace = (FrameLayout) findViewById(R.id.layout_drawing_workspace);
        layoutPenSetting = (FrameLayout) findViewById(R.id.layout_pen_setting);
        layoutPenCanvas = (RelativeLayout) findViewById(R.id.layout_pen_canvas);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnPenSetting = (ImageButton) findViewById(R.id.btn_pen_setting);
    }

    private void setup() {
        btnSave.setOnClickListener(this);
        btnPenSetting.setOnClickListener(this);

        if (setupSpen()) {
            setupDrawingWorkspace();
        } else {
            Toast.makeText(this, "Device doesn't support S pen.", Toast.LENGTH_SHORT).show();
            btnSave.setEnabled(false);
            btnPenSetting.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDrawingWorkspace();
    }

    private void closeDrawingWorkspace() {
        // TODO Add close drawing workspace code. This method will called by onDestroy()

    }

    private void setupDrawingWorkspace() {
        setupDrawingView();
        setupNoteDoc();
        setupPenSetting();
    }

    @SuppressWarnings("deprecation")
    private void setupDrawingView() {
        // TODO Initialize SpenSimpleSurfaceView, then add into drawing workspace layout

    }

    @SuppressWarnings("deprecation")
    private void setupNoteDoc() {
        // TODO Initialize SpenNoteDoc and create SpenPageDoc from SpenNoteDoc

    }

    private void setupPenSetting() {
        // TODO Initialize SpenSettingPenLayout, then add into pen setting layout
        // TODO Don't forget to set SpenSettingPenLayout canvas view with SpenSimpleSurfaceView

    }

    private void openPenSetting() {
        // TODO Call SpenSettingPenLayout method to open pen setting layout

    }

    private boolean setupSpen() {
        // TODO Initialize Pen SDK and return boolean result

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            // TODO Capture bitmap from SimpleSurfaceView
        } else if (v == btnPenSetting) {
            openPenSetting();
        }
    }
}

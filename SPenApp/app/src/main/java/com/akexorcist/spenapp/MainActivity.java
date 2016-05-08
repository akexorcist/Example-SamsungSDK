package com.akexorcist.spenapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.pen.Spen;
import com.samsung.android.sdk.pen.document.SpenNoteDoc;
import com.samsung.android.sdk.pen.document.SpenPageDoc;
import com.samsung.android.sdk.pen.engine.SpenSimpleSurfaceView;
import com.samsung.android.sdk.pen.settingui.SpenSettingPenLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout layoutDrawingWorkspace;
    private FrameLayout layoutPenSetting;
    private RelativeLayout layoutPenCanvas;
    private Button btnSave;
    private Button btnPenSetting;
    private Spen spen;
    private SpenNoteDoc spenNoteDoc;
    private SpenPageDoc spenPageDoc;
    private SpenSimpleSurfaceView spenSimpleSurfaceView;
    private SpenSettingPenLayout spenSettingPenLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutDrawingWorkspace = (FrameLayout) findViewById(R.id.layout_drawing_workspace);
        layoutPenSetting = (FrameLayout) findViewById(R.id.layout_pen_setting);
        layoutPenCanvas = (RelativeLayout) findViewById(R.id.layout_pen_canvas);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnPenSetting = (Button) findViewById(R.id.btn_pen_setting);
        btnSave.setOnClickListener(this);
        btnPenSetting.setOnClickListener(this);

        if (setupSpen()) {
            setupDrawingWorkspace();
        } else {
            Toast.makeText(this, "Device doesn't support S pen.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDrawingWorkspace();
    }

    private void closeDrawingWorkspace() {
        if (spenSimpleSurfaceView != null) {
            spenSimpleSurfaceView.close();
            spenSimpleSurfaceView = null;
        }
        if (spenNoteDoc != null) {
            try {
                spenNoteDoc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            spenNoteDoc = null;
        }
    }

    private void setupDrawingWorkspace() {
        setupDrawingView();
        setupNoteDoc();
        setupPenSetting();
    }

    private void setupDrawingView() {
        spenSimpleSurfaceView = new SpenSimpleSurfaceView(this);
        spenSimpleSurfaceView.setBlankColor(Color.GRAY);
        layoutDrawingWorkspace.addView(spenSimpleSurfaceView);
    }

    private void setupNoteDoc() {
        try {
            spenNoteDoc = new SpenNoteDoc(this, 1000, 1000);
            spenPageDoc = spenNoteDoc.appendPage();
            spenPageDoc.setBackgroundColor(Color.WHITE);
            spenSimpleSurfaceView.setPageDoc(spenPageDoc, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupPenSetting() {
        spenSettingPenLayout = new SpenSettingPenLayout(this, "", layoutPenCanvas);
        layoutPenSetting.addView(spenSettingPenLayout);
        spenSettingPenLayout.setCanvasView(spenSimpleSurfaceView);
    }

    private void openPenSetting() {
        spenSettingPenLayout.setViewMode(SpenSettingPenLayout.VIEW_MODE_NORMAL);
        spenSettingPenLayout.setVisibility(View.VISIBLE);
    }

    private boolean setupSpen() {
        spen = new Spen();
        try {
            spen.initialize(this);
            if (spen.isFeatureEnabled(Spen.DEVICE_PEN)) {
                return true;
            }
        } catch (SsdkUnsupportedException e) {
            // Error Handle
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            Bitmap drawingBitmap = spenSimpleSurfaceView.capturePage(1, SpenSimpleSurfaceView.CAPTURE_ALL);
            // Do something with this bitmap

        } else if (v == btnPenSetting) {
            openPenSetting();
        }
    }
}

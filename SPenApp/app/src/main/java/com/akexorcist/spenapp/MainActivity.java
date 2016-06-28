package com.akexorcist.spenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout layoutDrawingWorkspace;
    private FrameLayout layoutPenSetting;
    private RelativeLayout layoutPenCanvas;
    private TextView tvDeviceUnsupportedMessage;
    private Button btnSave;
    private ImageButton btnPenSetting;

    // TODO (1) : Declare Spen, SpenNoteDoc, SpenPageDoc, SpenSimpleSurfaceView and SpenSettingPenLayout instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        setup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDrawingWorkspace();
    }

    private void bindView() {
        layoutDrawingWorkspace = (FrameLayout) findViewById(R.id.layout_drawing_workspace);
        layoutPenSetting = (FrameLayout) findViewById(R.id.layout_pen_setting);
        layoutPenCanvas = (RelativeLayout) findViewById(R.id.layout_pen_canvas);
        tvDeviceUnsupportedMessage = (TextView) findViewById(R.id.tv_device_unsupported_message);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnPenSetting = (ImageButton) findViewById(R.id.btn_pen_setting);
    }

    private void setup() {
        btnSave.setOnClickListener(this);
        btnPenSetting.setOnClickListener(this);

        if (setupSpen()) {
            tvDeviceUnsupportedMessage.setVisibility(View.GONE);
            setupDrawingWorkspace();
        } else {
            tvDeviceUnsupportedMessage.setVisibility(View.VISIBLE);
            btnSave.setEnabled(false);
            btnPenSetting.setEnabled(false);
        }
    }

    private void setupDrawingWorkspace() {
        setupDrawingView();
        setupNoteDoc();
        setupPenSetting();
    }

    @SuppressWarnings("deprecation")
    private void setupDrawingView() {
        // TODO (2) : Initialize SpenSimpleSurfaceView, then add into drawing workspace layout

    }

    @SuppressWarnings("deprecation")
    private void setupNoteDoc() {
        // TODO (3) : Initialize SpenNoteDoc and create SpenPageDoc from SpenNoteDoc

    }

    private void setupPenSetting() {
        // TODO (4) : Initialize SpenSettingPenLayout, then add into pen setting layout
        // TODO (5) : Don't forget to set SpenSettingPenLayout canvas view with SpenSimpleSurfaceView

    }

    private void closeDrawingWorkspace() {
        // TODO (6) : Add close drawing workspace code. This method will called by onDestroy()

    }

    private void openPenSetting() {
        // TODO (7) : Call SpenSettingPenLayout method to open pen setting layout

    }

    private boolean setupSpen() {
        // TODO (8) : Initialize Pen SDK and return boolean result
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            captureDrawingBitmap();
        } else if (v == btnPenSetting) {
            openPenSetting();
        }
    }

    public void captureDrawingBitmap() {
        // TODO (9) : Capture bitmap from SimpleSurfaceView (Extra : Save bitmap to external storage as .jpg file)

    }
}

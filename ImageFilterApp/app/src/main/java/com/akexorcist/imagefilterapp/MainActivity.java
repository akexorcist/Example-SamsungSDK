package com.akexorcist.imagefilterapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.imagefilter.Sif;
import com.samsung.android.sdk.imagefilter.SifImageFilter;

public class MainActivity extends AppCompatActivity {
    private TextView tvImageSize;
    private TextView tvConvertTimeSepia;
    private TextView tvConvertTimeVintage;
    private TextView tvConvertTimePastelSketch;
    private ImageView ivOriginal;
    private ImageView ivSepia;
    private ImageView ivVintage;
    private ImageView ivPastelSketch;
    private Sif sif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvImageSize = (TextView) findViewById(R.id.tv_image_size);
        tvConvertTimeSepia = (TextView) findViewById(R.id.tv_convert_time_sepia);
        tvConvertTimeVintage = (TextView) findViewById(R.id.tv_convert_time_vintage);
        tvConvertTimePastelSketch = (TextView) findViewById(R.id.tv_convert_time_pastel_sketch);
        ivOriginal = (ImageView) findViewById(R.id.iv_original);
        ivSepia = (ImageView) findViewById(R.id.iv_sepia);
        ivVintage = (ImageView) findViewById(R.id.iv_vintage);
        ivPastelSketch = (ImageView) findViewById(R.id.iv_pastel_sketch);

        if (setupImageFilter()) {
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
            setOriginalImage(originalBitmap);
            setFilterImage(originalBitmap, SifImageFilter.FILTER_SEPIA, ivSepia, tvConvertTimeSepia);
            setFilterImage(originalBitmap, SifImageFilter.FILTER_VINTAGE, ivVintage, tvConvertTimeVintage);
            setFilterImage(originalBitmap, SifImageFilter.FILTER_PASTEL_SKETCH, ivPastelSketch, tvConvertTimePastelSketch);
        }
    }

    public void setOriginalImage(Bitmap originalBitmap) {
        int originalBitmapWidth = originalBitmap.getWidth();
        int originalBitmapHeight = originalBitmap.getHeight();
        tvImageSize.setText(String.format(getString(R.string.image_size), originalBitmapWidth, originalBitmapHeight));
        ivOriginal.setImageBitmap(originalBitmap);
    }

    public void setFilterImage(Bitmap originalBitmap, int filterType, ImageView ivFilteredBitmap, TextView tvConvertTime) {
        long beforeConvertTime = System.currentTimeMillis();
        Bitmap filteredBitmap = SifImageFilter.filterImageCopy(originalBitmap, filterType, SifImageFilter.LEVEL_MEDIUM);
        long convertTime = System.currentTimeMillis() - beforeConvertTime;
        ivFilteredBitmap.setImageBitmap(filteredBitmap);
        tvConvertTime.setText(String.format(getString(R.string.convert_time), convertTime));
    }

    public boolean setupImageFilter() {
        sif = new Sif();
        try {
            sif.initialize(this);
            return true;
        } catch (SsdkUnsupportedException e) {
            // Error Handling
            Toast.makeText(this, getImageFilterExceptionType(e.getType()), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public String getImageFilterExceptionType(int type) {
        switch (type) {
            case SsdkUnsupportedException.DEVICE_NOT_SUPPORTED:
                return "DEVICE_NOT_SUPPORTED";
            case SsdkUnsupportedException.LIBRARY_NOT_INSTALLED:
                return "LIBRARY_NOT_INSTALLED";
            case SsdkUnsupportedException.LIBRARY_UPDATE_IS_RECOMMENDED:
                return "LIBRARY_UPDATE_IS_RECOMMENDED";
            case SsdkUnsupportedException.LIBRARY_UPDATE_IS_REQUIRED:
                return "LIBRARY_UPDATE_IS_REQUIRED";
            case SsdkUnsupportedException.VENDOR_NOT_SUPPORTED:
                return "VENDOR_NOT_SUPPORTED";
            default:
                return "UNKNOWN_EXCEPTION";
        }
    }
}

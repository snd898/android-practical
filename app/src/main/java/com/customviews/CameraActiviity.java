package com.customviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.customviews.activities.ActivityA;

public class CameraActiviity extends AppCompatActivity implements View.OnClickListener {

    static final int RIC = 1;
    static final int RVC = 2;
    Button vCameraIntent, vVideoIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activiity);
        initViews();
    }

    private void initViews() {
        vCameraIntent = findViewById(R.id.vCameraIntent);
        vVideoIntent = findViewById(R.id.vVideoIntent);

        vCameraIntent.setOnClickListener(this);
        vVideoIntent.setOnClickListener(this);

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            if (takePictureIntent.resolveActivity(getPackageManager())!=null) {
                startActivityForResult(takePictureIntent, RIC);
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, RVC);
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(vCameraIntent)) {
            dispatchTakePictureIntent();
        } else if (v.equals(vVideoIntent)) {
            dispatchTakeVideoIntent();
        }
    }
}
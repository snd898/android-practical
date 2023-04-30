package com.customviews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.customviews.R;
import com.customviews.tasks.GetTestInfo;

public class ActivityD extends AppCompatActivity {

    private Button vButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        vButton = findViewById(R.id.vButton);
        vButton.setOnClickListener(v -> startActivity(new Intent(
                ActivityD.this,
                ActivityA.class
        )));
        new GetTestInfo().getTaskInfo(this);
    }
}
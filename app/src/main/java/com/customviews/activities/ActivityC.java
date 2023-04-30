package com.customviews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.customviews.R;
import com.customviews.tasks.GetTestInfo;

public class ActivityC extends AppCompatActivity {
    private Button vButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        vButton = findViewById(R.id.vButton);
        vButton.setOnClickListener(v -> startActivity(new Intent(
                ActivityC.this,
                ActivityD.class
        )));
        new GetTestInfo().getTaskInfo(this);
    }
}
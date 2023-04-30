package com.customviews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.customviews.Const;
import com.customviews.R;
import com.customviews.tasks.GetTestInfo;

public class ActivityB extends AppCompatActivity {

    private Button vButton;
    private Button vSingleTop;
    private Button vStandard;
    private Button vSingleInstance;

    private TextView vNumberOfInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        vButton = findViewById(R.id.vButton);
        vSingleTop = findViewById(R.id.vSingleTop);
        vStandard = findViewById(R.id.vStandard);
        vSingleInstance = findViewById(R.id.vSingleInstance);
        vNumberOfInstance = findViewById(R.id.vNumberOfInstance);
        Const.instanceCount++;

        vNumberOfInstance.setText(Const.instanceCount+"");
        vButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityB.this, ActivityC.class);
                startActivity(intent);
            }
        });

        vStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityB.this, ActivityB.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        vSingleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityB.this, ActivityB.class);
                startActivity(intent);
            }
        });
        vSingleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityB.this, ActivityB.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        new GetTestInfo().getTaskInfo(this);
    }
}
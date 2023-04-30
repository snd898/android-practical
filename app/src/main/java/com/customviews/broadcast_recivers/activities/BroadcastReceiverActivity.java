package com.customviews.broadcast_recivers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.customviews.R;
import com.customviews.broadcast_recivers.activities.airplane_mode.AirplaneModeReceiver;

public class BroadcastReceiverActivity extends AppCompatActivity {
    private Button vButton;
    private AirplaneModeReceiver airplaneModeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        vButton = findViewById(R.id.vSendBroadcast);

//        Intent intent = new Intent();
//        intent.putExtra("name", "Yash");
//        intent.setAction("com.custom.ButtonClickBroadCastIntent");
//
//        vButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendBroadcast(intent);
//                //startActivity(new Intent(MainAct  ivity.this, ActivityA.class));
//            }
//        });

        airplaneModeReceiver = new AirplaneModeReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(airplaneModeReceiver);
        super.onDestroy();
    }
}
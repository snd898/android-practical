package com.customviews.broadcast_recivers.activities.airplane_mode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            if (isOn(context)) {
                Toast.makeText(context, "Airplane mode is on", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Airplane mode is off", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}

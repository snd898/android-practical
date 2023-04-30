package notification_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.customviews.R;

public class NewNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CHANNEL_ID = "channel_two";
    Button vShowNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);
        vShowNotification = findViewById(R.id.vShowNotification);
        createNotificationChannel();

        vShowNotification.setOnClickListener(this);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 2";
            String description = "Subsrcibe to my channel...";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onClick(View v) {
        if (v==vShowNotification) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.baby)
                    .setContentTitle("New Baby")
                    .setContentText("Just arrived")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
            nmc.notify(1, builder.build());
        }
    }
}
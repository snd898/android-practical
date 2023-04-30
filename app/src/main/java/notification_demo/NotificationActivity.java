package notification_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.customviews.R;

import activity_and_lifecycle.SampleActivity;
import mydialogs.ChangeNameDailog;
import mydialogs.DateSelectionDailog;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private final String channelName = "channel1";
    private final String channelNameDisplay = "Channel 1";
    int notificationId = 1;
    private Button vShowNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        vShowNotification = findViewById(R.id.vShowNotification);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelName, channelNameDisplay, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        vShowNotification.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v==vShowNotification) {
            ChangeNameDailog changeNameDailog = new ChangeNameDailog();
            changeNameDailog.show(getSupportFragmentManager(), "ChangeNameDailog");
//            DialogFragment dateSelectionDailog = new DateSelectionDailog();
//            dateSelectionDailog.show(getSupportFragmentManager(), "DailogFragment");
            // Create an explicit intent for an Activity in your app
            /*Intent intent = new Intent(this, SampleActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, channelName)
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentTitle("New Notification")
                    .setContentText("Please take a look on new notification")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line...")
                    )
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.ic_stat_name, "Snooze", pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(notificationId, notificationCompat.build());*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mCreateNew:
                Toast.makeText(this, "Create New is clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mOpen:
                Toast.makeText(this, "Open is clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
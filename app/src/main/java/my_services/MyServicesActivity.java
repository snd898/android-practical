package my_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.customviews.R;

public class MyServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);
        Intent serviceIntent = new Intent(this, HelloService.class);
        startForegroundService(serviceIntent);
    }
}
package com.customviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.customviews.my_locations.MyLocationActivity;
import com.customviews.tasks.GetTestInfo;
import com.customviews.activities.ActivityA;

import activity_and_lifecycle.ActivityAndLifecycle;
import content_providers.ContentProviderActivity;
import content_providers.dictionery_demo.DictionaryActivity;
import my_services.MyServicesActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button vActivityLifecycle;
    Button vContentProvider;
    Button vService;
    Button vContentProviderDictionary;
    Button vLocation;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        while (flag) {
//            System.out.println("While is calling");
//        }
        initViews();
        registerClick();
    }

    public void initViews() {
        vActivityLifecycle = findViewById(R.id.vActivityLifecycle);
        vContentProvider = findViewById(R.id.vContentProvider);
        vContentProviderDictionary = findViewById(R.id.vContentProviderDictionary);
        vService = findViewById(R.id.vService);
        vLocation = findViewById(R.id.vLocation);
    }
    public void registerClick() {
        vActivityLifecycle.setOnClickListener(this);
        vContentProvider.setOnClickListener(this);
        vContentProviderDictionary.setOnClickListener(this);
        vService.setOnClickListener(this);
        vLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (vActivityLifecycle == v) {
            Intent intent = new Intent(MainActivity.this, ActivityAndLifecycle.class);
            startActivity(intent);
        } else if (vContentProvider == v) {
            Intent intent = new Intent(MainActivity.this, ContentProviderActivity.class);
            startActivity(intent);
        } else if (vContentProviderDictionary == v) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
            startActivity(intent);
        } else if (vService == v) {
            Intent intent = new Intent(MainActivity.this, MyServicesActivity.class);
            startActivity(intent);
        } else if (vLocation == v) {
            Intent intent = new Intent(MainActivity.this, MyLocationActivity.class);
            startActivity(intent);
        }
    }
}
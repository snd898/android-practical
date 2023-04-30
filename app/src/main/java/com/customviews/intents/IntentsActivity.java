package com.customviews.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.customviews.R;

import java.util.ArrayList;
import java.util.List;

public class IntentsActivity extends AppCompatActivity {

    Button vSendSms;
    private Spinner vFruits;
    private RecyclerView vListView;
    private FruitAdapter fruitAdapter;
    private List<String> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
        vFruits = findViewById(R.id.vFruits);
        vListView = findViewById(R.id.vListView);

//        vSendSms = findViewById(R.id.vSendSms);
//        vSendSms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setData(Uri.parse("sms:9026496520"));
//                intent.putExtra("sms_body","Hello Android developers");
//                startActivity(intent);
//            }
//        });

//        ArrayAdapter<String> fruitsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, new String[]{"Apple", "Mango", "Guava", "Banana", "Orange"});
//        vFruits.setAdapter(fruitsAdapter);

//        for(int i = 0; i < 30 ; i++) {
//            fruits.add("Sample "+i);
//        }
        fruits.add("Sample ");
        fruits.add("Sample ");
        fruits.add("Sample ");
        fruits.add("Sample ");
        fruits.add("Sample ");
        fruits.add("Sample ");
        fruits.add("Sample ");
        fruits.add("Sample " );

        fruitAdapter = new FruitAdapter();
        fruitAdapter.submitList(fruits);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        vListView.setLayoutManager(linearLayoutManager);
        vListView.setAdapter(fruitAdapter);
    }
}
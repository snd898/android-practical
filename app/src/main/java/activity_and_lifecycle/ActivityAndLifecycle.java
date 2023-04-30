package activity_and_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.customviews.R;

import java.util.ArrayList;
import java.util.List;

// public class ActivityAndLifecycle extends Activity {
public class ActivityAndLifecycle extends AppCompatActivity {

    List<String> list = new ArrayList<>();
    Button vShow;
    Button vReset;
    Button vNewActivity;
    Button vNewActAndReset;
    TextView vTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_lifecycle);
        vShow = findViewById(R.id.vShow);
        vReset = findViewById(R.id.vResetLifecycle);
        vNewActivity = findViewById(R.id.vNewActivity);
        vNewActAndReset = findViewById(R.id.vNewActAndReset);
        vTextView = findViewById(R.id.vTextView);
        list.add("onCreate");

        vShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String lifecycle: list) {
                    stringBuilder.append(lifecycle).append("\n");
                }
                vTextView.setText(stringBuilder.toString());
            }
        });
        vReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                vTextView.setText("");
            }
        });
        vNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAndLifecycle.this, SampleActivity.class);
                startActivity(intent);
            }
        });
        vNewActAndReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                Intent intent = new Intent(ActivityAndLifecycle.this, SampleActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        list.add("onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        list.add("onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        list.add("onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        list.add("onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        list.add("onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        list.add("onDestroy");
        super.onDestroy();
    }
}
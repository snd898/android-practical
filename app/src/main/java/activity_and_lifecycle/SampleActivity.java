package activity_and_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.customviews.R;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplle);
    }
}
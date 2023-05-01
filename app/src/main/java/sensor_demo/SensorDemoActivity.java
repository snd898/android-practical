package sensor_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.customviews.R;

import java.util.List;

public class SensorDemoActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor mLight;
    Sensor mProximitySensor;
    TextView vSensorData;
    ImageView vImageLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_demo);
        vSensorData = findViewById(R.id.vSensorData);
        vImageLight = findViewById(R.id.vImageLight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sensorManager = getSystemService(SensorManager.class);
        }
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //mProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        vSensorData.setText(lux+"");
        if (lux==0) {
            vImageLight.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            vImageLight.setBackgroundColor(getResources().getColor(R.color.yellow));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
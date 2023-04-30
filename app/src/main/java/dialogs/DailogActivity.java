package dialogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.customviews.R;

import java.util.Calendar;

public class DailogActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    Button vShowDate, vShowTime, vShowDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailog);
        vShowDate = findViewById(R.id.vShowDate);
        vShowTime = findViewById(R.id.vShowTime);
        vShowDialog = findViewById(R.id.vShowDialog);

        vShowDate.setOnClickListener(this);
        vShowTime.setOnClickListener(this);
        vShowDialog.setOnClickListener(this);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(this, "HH: "+hourOfDay+" MM: "+minute, Toast.LENGTH_SHORT).show();
    }

    public void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        new TimePickerDialog(DailogActivity.this, DailogActivity.this, hour, minute, DateFormat.is24HourFormat(this)).show();

    }

    @Override
    public void onClick(View v) {
        if (v==vShowDate) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2019, 10, 19);
                datePickerDialog.show();
            }
        } else if (v==vShowTime) {
            showTimePicker();
        } else if (v==vShowDialog) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.contact, null);
            builder.setView(view);
            TextView textView = view.findViewById(R.id.vFruitName);
            textView.setText("Apple");
//            builder.setTitle("Dialog");
//            builder.setMessage("This is a new Dialog");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DailogActivity.this, "This is ok button click", Toast.LENGTH_SHORT).show();
                }
            });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(DailogActivity.this, "This is Cancel button click", Toast.LENGTH_SHORT).show();
//                }
//            });
//            builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(DailogActivity.this, "This is No button click", Toast.LENGTH_SHORT).show();
//                }
//            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, "Year: "+year+" Month: "+month+ "dayOfMonth: "+dayOfMonth, Toast.LENGTH_SHORT).show();
    }
}
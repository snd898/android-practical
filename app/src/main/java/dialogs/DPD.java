package dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DPD extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private Context context;
    public DPD(Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(context, this, year, month, day);
        } else {
            return null;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(context, "Year: "+year+" Month: "+month+ "dayOfMonth: "+dayOfMonth, Toast.LENGTH_SHORT).show();
        //}
    }
}

package mydialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.customviews.R;

public class ChangeNameDailog extends DialogFragment implements View.OnClickListener{

    EditText vFileName;
    Button vCancel;
    Button vOk;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.change_file_name, null);
        vFileName = view.findViewById(R.id.vFileName);
        vCancel = view.findViewById(R.id.vCancel);
        vOk = view.findViewById(R.id.vOk);
        builder.setView(view);

        vCancel.setOnClickListener(this);
        vOk.setOnClickListener(this);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (vCancel==v) {
            dismiss();
        } else if (vOk==v) {
            if (vFileName.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Please enter he name", Toast.LENGTH_SHORT).show();
            } else {
                dismiss();
            }
        }
    }
}

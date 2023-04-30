package content_providers.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.customviews.R;

import content_providers.UpdateContact;

public class EditContact extends DialogFragment implements View.OnClickListener {

    EditText vContactName;
    EditText vContactNumber;
    Button vCancel;
    Button vOk;
    UpdateContact updateContact;
    String rowId;
    public EditContact(UpdateContact updateContact, String rowId) {
        this.updateContact = updateContact;
        this.rowId = rowId;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_contact_view, null);
        vContactName = view.findViewById(R.id.vContactName);
        vContactNumber = view.findViewById(R.id.vContactNumber);
        vCancel = view.findViewById(R.id.vCancel);
        vOk = view.findViewById(R.id.vOk);

        vCancel.setOnClickListener(this);
        vOk.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        if (vCancel==v) {
            dismiss();
        } else if (vOk == v) {
            updateContact.updateContact(vContactName.getText().toString(), vContactNumber.getText().toString(), rowId);
            dismiss();
        }
    }
}

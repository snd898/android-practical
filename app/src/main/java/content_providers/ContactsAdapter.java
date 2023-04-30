package content_providers;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.customviews.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {
    private List<MyPhone> phoneList;
    private UpdateContact updateContact;
    public ContactsAdapter(List<MyPhone> phoneList, UpdateContact updateContact) {
        this.phoneList = phoneList;
        this.updateContact = updateContact;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_number_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.vName.setText(phoneList.get(position).name);
        holder.vPhoneNumber.setText(phoneList.get(position).number);
        holder.vEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact.openEditContact(phoneList.get(position).name, phoneList.get(position).number);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        TextView vName, vPhoneNumber;
        ImageButton vEdit;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            vName = itemView.findViewById(R.id.vName);
            vPhoneNumber = itemView.findViewById(R.id.vPhoneNumber);
            vEdit = itemView.findViewById(R.id.vEdit);
        }
    }
}

package content_providers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.customviews.R;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import content_providers.dialogs.EditContact;

//https://developer.android.com/reference/android/provider/ContactsContract.Data
// DISPLAY NAME is read only.
public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener, UpdateContact {

    RecyclerView vPhoneList;
    Button vLoadContact;
    ContactsAdapter contactsAdapter;
    List<MyPhone> phoneList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        vPhoneList = findViewById(R.id.vPhoneList);
        vLoadContact = findViewById(R.id.vLoadContact);
        phoneList = new ArrayList<>();

        vLoadContact.setOnClickListener(this);
        askPermission();
    }

    @Override
    public void onClick(View v) {
        if (vLoadContact == v) {
            loadContactList();
        }
    }

    private void loadContactList() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                },
                null,
                null,
                null
        );
        if (cursor.getCount()>0) {
            phoneList = new ArrayList<>();
            while (cursor.moveToNext()) {
                MyPhone myPhone = new MyPhone();
                myPhone .name = cursor.getString(0);
                myPhone.number = cursor.getString(1);
                phoneList.add(myPhone);
            }
        }
        contactsAdapter = new ContactsAdapter(phoneList, this);
        vPhoneList.setLayoutManager(new LinearLayoutManager(this));
        vPhoneList.setAdapter(contactsAdapter);
    }

    private void updateContact(MyPhone myPhone) {



    }

    public void askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[2];
            permissions[0] = Manifest.permission.WRITE_CONTACTS;
            permissions[1] = Manifest.permission.READ_CONTACTS;
            if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void updateContact(String name, String number, String rowId) {
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        operations.add(
                ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(
                        ""+ContactsContract.Data._ID+" = ?",
                        new String[]{rowId}
                )
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .build());

        ContentResolver cr = getContentResolver();
        try {
            cr. applyBatch(ContactsContract.AUTHORITY, operations);
            Toast.makeText(this, "Phone number is updated", Toast.LENGTH_SHORT).show();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openEditContact(String name, String number) {
//        Cursor idCursor = getContentResolver().query(
//                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                new String[]{ContactsContract.Data._ID},
//                ""+ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" = ?",
//                new String[]{name},
//                null
//        );
        Cursor idCursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.Data._ID, ContactsContract.CommonDataKinds.Phone._ID},
                ""+ContactsContract.CommonDataKinds.Phone.NUMBER+" = ?",
                new String[]{number},
                null
        );
        if (idCursor.getCount()> 0) {
            idCursor.moveToNext();
            String ID = idCursor.getString(0);
            String phoneId = idCursor.getString(1);
            EditContact editContact = new EditContact(this, ID);
            editContact.show(getSupportFragmentManager(), "EditContact");
        } else {
            Toast.makeText(this, "ID is not found for "+name, Toast.LENGTH_SHORT).show();
        }

    }
}
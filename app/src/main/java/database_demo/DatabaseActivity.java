package database_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.customviews.R;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener{

    private DBHelper dbHelper;
    private Button vInsertUser;
    private Button vDeleteUser;
    private EditText vUserId;
    private EditText vName;
    private EditText vAge;
    private Button vUpdateUser;
    private SQLiteDatabase db;
    private DHelper dHelper;
    private RecyclerView vUserList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        vInsertUser = findViewById(R.id.vInsertUser);
        vDeleteUser = findViewById(R.id.vDeleteUser);
        vUpdateUser = findViewById(R.id.vUpdateUser);
        vUserId = findViewById(R.id.vUserId);
        vName = findViewById(R.id.vName);
        vAge = findViewById(R.id.vAge);
        vUserList = findViewById(R.id.vUserList);

        dHelper = new DHelper(this);
        dbHelper = new DBHelper(this);

        vInsertUser.setOnClickListener(this);
        vDeleteUser.setOnClickListener(this);
        vUpdateUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (vInsertUser == v) {
            db = dHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "Apple");
            contentValues.put("life", 2);
            db.insert("fruit", null, contentValues);
            db.close();
        } else if (vUpdateUser==v) {
            db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", vName.getText().toString());
            contentValues.put("age", vAge.getText().toString());
            db.update("user", contentValues, "id = ?", new String[]{vUserId.getText().toString()});
            db.close();
        } else if (vDeleteUser == v) {
            db = dbHelper.getWritableDatabase();
            db.delete("user", "id = ?", new String[]{vUserId.getText().toString()});
            db.close();
        }
    }
}
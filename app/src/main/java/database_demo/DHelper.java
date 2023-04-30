package database_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DB_Name";
    public static final int VERSION = 1;

    public static final String FRUIT = "CREATE TABLE FRUIT (id INTEGER primary key autoincrement, name TEXT, life INTEGER);";

    public DHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public DHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FRUIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

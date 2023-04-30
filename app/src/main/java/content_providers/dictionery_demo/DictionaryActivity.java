package content_providers.dictionery_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.widget.ListView;
import android.widget.Toast;

import com.customviews.R;


//        word	        app id	frequency	locale	_ID
//        mapreduce	    user1	100	        en_US	1
//        precompiler	user14	200	        fr_FR	2
//        applet	    user2	225	        fr_CA	3
//        const	        user1	255	        pt_BR	4
//        int	        user5	100	        en_UK	5


//content://user_dictionary/words
public class DictionaryActivity extends AppCompatActivity {

    Cursor mCursor;
    // A "projection" defines the columns that will be returned for each row
    String[] mProjection =
            {
                    UserDictionary.Words._ID,    // Contract class constant for the _ID column name
                    UserDictionary.Words.WORD,   // Contract class constant for the word column name
                    UserDictionary.Words.LOCALE  // Contract class constant for the locale column name
            };

    // Defines a string to contain the selection clause
    String selectionClause = null;

    // Initializes an array to contain selection arguments
    String[] selectionArgs = {""};
    ListView wordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionery);
        wordList = findViewById(R.id.wordList);

        // Queries the user dictionary and returns results
        mCursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
                mProjection,                        // The columns to return for each row
                null,                   // Selection criteria
                null,                     // Selection criteria
                null);                        // The sort order for the returned rows

        // Some providers return null if an error occurs, others throw an exception
        if (null == mCursor) {
            /*
             * Insert code here to handle the error. Be sure not to use the cursor! You may want to
             * call android.util.Log.e() to log this error.
             *
             */
        // If the Cursor is empty, the provider found no matches
        } else if (mCursor.getCount() < 1) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            /*
             * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily
             * an error. You may want to offer the user the option to insert a new row, or re-type the
             * search term.
             */

        } else {
            // Insert code here to do something with the results
// Defines a list of columns to retrieve from the Cursor and load into an output row
            String[] wordListColumns =
                    {
                            UserDictionary.Words.WORD,   // Contract class constant containing the word column name
                            UserDictionary.Words.LOCALE  // Contract class constant containing the locale column name
                    };

            // Defines a list of View IDs that will receive the Cursor columns for each row
            int[] wordListItems = { R.id.dictWord, R.id.locale};

            // Creates a new SimpleCursorAdapter
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                    getApplicationContext(),               // The application's Context object
                    R.layout.wordlistrow,                  // A layout in XML for one row in the ListView
                    mCursor,                               // The result from the query
                    wordListColumns,                      // A string array of column names in the cursor
                    wordListItems,                        // An integer array of view IDs in the row layout
                    0);                                    // Flags (usually none are needed)

            // Sets the adapter for the ListView
            wordList.setAdapter(cursorAdapter);
        }
    }
}
package media_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.customviews.R;

import java.io.IOException;

public class VideoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        //        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sample);
//        mediaPlayer.start(); // no need to call prepare(); create() does that for you

//        ContentResolver contentResolver = getContentResolver();
//        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor cursor = contentResolver.query(uri, null, null, null, null);
//        if (cursor == null) {
//            // query failed, handle error.
//        } else if (!cursor.moveToFirst()) {
//            // no media on the device
//        } else {
//            int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
//            int idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
//            do {
//                long thisId = cursor.getLong(idColumn);
//                String thisTitle = cursor.getString(titleColumn);
//                // ...process entry...
//            } while (cursor.moveToNext());
//
//            long id = /* retrieve it from somewhere */;
//            Uri contentUri = ContentUris.withAppendedId(
//                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
//
//            MediaPlayer mediaPlayer = new MediaPlayer();
//            mediaPlayer.setAudioAttributes(
//                    new AudioAttributes.Builder()
//                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                            .setUsage(AudioAttributes.USAGE_MEDIA)
//                            .build()
//            );
//            try {
//                mediaPlayer.setDataSource(getApplicationContext(), contentUri);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
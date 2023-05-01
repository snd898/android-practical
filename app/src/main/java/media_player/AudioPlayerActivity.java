package media_player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.customviews.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import media_player.interfaces.IPlayAudio;

public class AudioPlayerActivity extends AppCompatActivity implements View.OnClickListener , IPlayAudio {

    Button vPlay, vPause;
    MediaPlayer mediaPlayer;
    Uri uri;
    List<AudioData> audioDataList;
    AudioListAdapter audioListAdapter;
    RecyclerView vAudioList;
    String[] permissions = new String[]{
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        vPlay = findViewById(R.id.vPlay);
        vPause = findViewById(R.id.vPause);
        vAudioList = findViewById(R.id.vAudioList);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO)!= PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(permissions,1);
            }
        }
        mediaPlayer = new MediaPlayer();
        uri = Uri.parse("android.resource://"+getPackageName()+"/raw/sample");

        mediaPlayer.setAudioAttributes(
                new AudioAttributes .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        loadAudioList();
        vPlay.setOnClickListener(this);
        vPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (vPlay == v) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(this, uri);
                if (mediaPlayer.isPlaying()) {
                    return;
                }
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        } else if (vPause == v) {
            try {
                mediaPlayer.stop();
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadAudioList() {
        audioDataList = new ArrayList<>();
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor == null) {
                // query failed, handle error.
            } else if (!cursor.moveToFirst()) {
                // no media on the device
            } else {
                int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
                int idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
                do {
                    AudioData audioData= new AudioData();
                    long thisId = cursor.getLong(idColumn);
                    String thisTitle = cursor.getString(titleColumn);
                    audioData.id = thisId;
                    audioData.name = thisTitle;
                    audioDataList.add(audioData);
                    // ...process entry...
                } while (cursor.moveToNext());

                audioListAdapter = new AudioListAdapter(audioDataList, this);
                vAudioList.setLayoutManager(new LinearLayoutManager(this));
                vAudioList.setAdapter(audioListAdapter);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playAudio(Long id, String name, ImageButton vImageButton) {
        vImageButton.setImageResource(R.drawable.baseline_pause_24);
        Uri contentUri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(getApplicationContext(), contentUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void pauseAudio(Long id, ImageButton vImageButton) {
        vImageButton.setImageResource(R.drawable.baseline_play_arrow_24);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1) {
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
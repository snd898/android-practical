package media_player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.customviews.R;

import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    Button vPlay, vPause;
    MediaPlayer mediaPlayer;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        vPlay = findViewById(R.id.vPlay);
        vPause = findViewById(R.id.vPause);

        mediaPlayer = new MediaPlayer();
        uri = Uri.parse("android.resource://"+getPackageName()+"/raw/sample");

        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(this, uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        vPlay.setOnClickListener(this);
        vPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (vPlay == v) {
            try {
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
}
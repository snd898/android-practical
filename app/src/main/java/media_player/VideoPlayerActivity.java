package media_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.customviews.R;

import java.io.IOException;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView vVideoView;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        vVideoView = findViewById(R.id.vVideoView);
        uri = Uri.parse("android.resource://"+getPackageName()+"/raw/video_sample");
        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(vVideoView);
        //specify the location of media file

        //Setting MediaController and URI, then starting the videoView
        vVideoView.setMediaController(mediaController);
        vVideoView.setVideoURI(uri);
        vVideoView.requestFocus();
        vVideoView.start();
    }
}
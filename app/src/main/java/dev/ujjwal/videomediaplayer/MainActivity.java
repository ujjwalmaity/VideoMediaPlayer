package dev.ujjwal.videomediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mere_haath_mein);
        videoView.start();

        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }

    private void init() {
        videoView = findViewById(R.id.videoView);

        mediaController = new MediaController(this);
    }
}

package dev.ujjwal.videomediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;

    Button play_pause, replay, stop;

    SeekBar seekBar;
    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mere_haath_mein);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(videoView.getDuration());
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        play_pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    private void init() {
        videoView = findViewById(R.id.videoView);

        play_pause = findViewById(R.id.play_pause);
        replay = findViewById(R.id.replay);
        stop = findViewById(R.id.stop);

        seekBar = findViewById(R.id.seekBar);
        handler = new Handler();
    }

    private void changeSeekBar() {
        seekBar.setProgress(videoView.getCurrentPosition());

        runnable = new Runnable() {
            @Override
            public void run() {
                changeSeekBar();
            }
        };
        handler.postDelayed(runnable, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    play_pause.setText("PLAY");
                    break;
                }
                videoView.start();
                play_pause.setText("PAUSE");
                changeSeekBar();
                break;
            case R.id.replay:
                videoView.resume();
                break;
            case R.id.stop:
                videoView.suspend();
                break;
        }
    }
}

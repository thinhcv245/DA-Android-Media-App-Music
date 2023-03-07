package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class PlayMusicActivity extends AppCompatActivity {

    ImageView imageViewRotate;
    TextView textViewStartTime, textViewEndTime;
    //Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_circle);
    String url = "https://cdn.pixabay.com/download/audio/2021/12/16/audio_232a4bdedf.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        System.out.println("1");
        imageViewRotate = findViewById(R.id.playmusic_imageView_rotate);
        textViewStartTime = findViewById(R.id.playmusic_textView_timeStart);
        textViewEndTime = findViewById(R.id.playmusic_textView_timeEnd);
        System.out.println("2");
        //imageViewRotate.startAnimation(rotateAnimation);
        MediaPlayer mediaPlayer = new MediaPlayer();
        System.out.println("3");
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        System.out.println("4");
        try {
            System.out.println("Vô");
            mediaPlayer.setDataSource(url);
            System.out.println("load");

            mediaPlayer.prepareAsync();
            System.out.println("load2");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    System.out.println(mp);
                    System.out.println(mp.getDuration());
                    SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
                    textViewEndTime.setText(hourseFormat.format(mp.getDuration()));
                    mp.start();
                }
            });
        } catch (IOException e) {
            Toast.makeText(this, "Bạn không cấp quyền nên không thể thực hiện.", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.mediaappmusic.Services;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class MediaPlayerService {
    public static MediaPlayer createMediaPlayer(Context context, String url, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation, SeekBar seekBarSong, TextView textViewStartTime, TextView textViewEndTime) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    setTimeTotal(mp, seekBarSong, textViewEndTime);
                    imageViewRotate.startAnimation(rotateAnimation);
                    mp.start();
                }
            });
            updateTimeStart(mediaPlayer, seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate);
        } catch (IOException e) {
            Toast.makeText(context, "Bạn không cấp quyền nên không thể thực hiện.", Toast.LENGTH_SHORT).show();
        }
        return mediaPlayer;
    }
    public static void setTimeTotal(MediaPlayer mediaPlayer, SeekBar seekBarSong, TextView textViewEndTime) {
        SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
        textViewEndTime.setText(hourseFormat.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }
    public static void updateTimeStart(MediaPlayer mediaPlayer, SeekBar seekBarSong, TextView textViewStartTime, ImageView imageViewPlayOrPause, ImageView imageViewRotate ) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
                textViewStartTime.setText(hourseFormat.format(mediaPlayer.getCurrentPosition()));
                seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                if(seekBarSong.getMax() == seekBarSong.getProgress() && seekBarSong.getMax() == mediaPlayer.getCurrentPosition()) {
                    pauseSong(mediaPlayer, imageViewPlayOrPause, imageViewRotate);
                    seekBarSong.setProgress(0);
                    textViewStartTime.setText("00:00");
                    return;
                }
                handler.postDelayed(this, 500);
            }
        },500);
    }
    public static void playSong(MediaPlayer mediaPlayer, SeekBar seekBarSong, TextView textViewStartTime, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation) {
        imageViewPlayOrPause.setImageResource(R.drawable.icon_play);
        imageViewRotate.startAnimation(rotateAnimation);
        mediaPlayer.start();
        updateTimeStart(mediaPlayer, seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate);
    }
    public static void pauseSong(MediaPlayer mediaPlayer, ImageView imageViewPlayOrPause, ImageView imageViewRotate) {
        imageViewPlayOrPause.setImageResource(R.drawable.icon_pause);
        imageViewRotate.clearAnimation();
        mediaPlayer.pause();
    }
}

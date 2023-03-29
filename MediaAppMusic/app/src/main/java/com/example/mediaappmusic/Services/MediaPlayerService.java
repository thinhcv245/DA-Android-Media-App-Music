package com.example.mediaappmusic.Services;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MediaPlayerService {
    private static ArrayList<SongDTO> songs;
    private static int position = 0;

    private static MediaPlayer mediaPlayer;
    private static MediaPlayerService instance;
    private MediaPlayerService() {}
    public static MediaPlayerService getInstance() {
        if(instance == null)
            instance = new MediaPlayerService();
        return instance;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

//    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
//        MediaPlayerService.mediaPlayer = mediaPlayer;
//    }

    public static ArrayList<SongDTO> getSongs() {
        return songs;
    }

    public static void setSongs(ArrayList<SongDTO> songs) {
        MediaPlayerService.songs = songs;
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        MediaPlayerService.position = position;
    }

    public void createMediaPlayer(Context context, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation, SeekBar seekBarSong, TextView textViewStartTime, TextView textViewEndTime) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            if(songs != null) {
                mediaPlayer.reset();
                mediaPlayer.setDataSource("https://mp3-s1-zmp3.zmdcdn.me/30849dddcb9c22c27b8d/7013518746570810032?authen=exp=1680233664~acl=/30849dddcb9c22c27b8d/*~hmac=f0b06f04d8c1f461f760c2994696f483&fs=MTY4MDA2MDg2NDA2M3x3ZWJWNnwwfDU0LjI1NC4xNjIdUngMTM4");
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        setTimeTotal(seekBarSong, textViewEndTime);
                        imageViewRotate.startAnimation(rotateAnimation);
                        mp.start();
                    }
                });
                updateTimeStart(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate);
            }
        } catch (IOException e) {
            Toast.makeText(context, "Bạn không cấp quyền nên không thể thực hiện.", Toast.LENGTH_SHORT).show();
        }
    }
    public void setTimeTotal(SeekBar seekBarSong, TextView textViewEndTime) {
        SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
        textViewEndTime.setText(hourseFormat.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }
    public void updateTimeStart(SeekBar seekBarSong, TextView textViewStartTime, ImageView imageViewPlayOrPause, ImageView imageViewRotate ) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
                textViewStartTime.setText(hourseFormat.format(mediaPlayer.getCurrentPosition()));
                seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                if(seekBarSong.getMax() == seekBarSong.getProgress() && seekBarSong.getMax() == mediaPlayer.getCurrentPosition()) {
                    pauseSong(imageViewPlayOrPause, imageViewRotate);
                    seekBarSong.setProgress(0);
                    textViewStartTime.setText("00:00");
                    return;
                }
                handler.postDelayed(this, 500);
            }
        },500);
    }
    public void playSong(SeekBar seekBarSong, TextView textViewStartTime, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation) {
        imageViewPlayOrPause.setImageResource(R.drawable.icon_play);
        imageViewRotate.startAnimation(rotateAnimation);
        mediaPlayer.start();
        updateTimeStart(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate);
    }
    public void pauseSong(ImageView imageViewPlayOrPause, ImageView imageViewRotate) {
        imageViewPlayOrPause.setImageResource(R.drawable.icon_pause);
        imageViewRotate.clearAnimation();
        mediaPlayer.pause();
    }
}

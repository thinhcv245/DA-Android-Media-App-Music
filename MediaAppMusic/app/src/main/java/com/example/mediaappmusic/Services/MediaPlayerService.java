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
import com.example.mediaappmusic.DTO.StreamSongDTO;
import com.example.mediaappmusic.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MediaPlayerService {
    private static ArrayList<SongDTO> songs;
    private static Gson gson = new Gson();
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
                StreamSongDTO stream = gson.fromJson(APIService.getInstance().getStreaming(songs.get(position).getEncodeId())
                        , StreamSongDTO.class);
                String url = stream.get_128();
                if(url == null || url.contains("\"err\"")) {
                    Toast.makeText(imageViewPlayOrPause.getContext(), "Bài hát đang lỗi", Toast.LENGTH_LONG).show();
                }
                else {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(url);
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

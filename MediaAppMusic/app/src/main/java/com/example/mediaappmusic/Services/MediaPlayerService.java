package com.example.mediaappmusic.Services;

import android.content.Context;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.mediaappmusic.Adapters.SongAdapter;
import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.DTO.StreamSongDTO;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MediaPlayerService {
    public static int PAUSE = 1;
    public static int UN_PAUSE = 0;
    public static int PLAY_DEFAULT = -1;
    public static int PLAY_REPEAT = 0;
    public static int PLAY_RANDOM = 1;

    Context context = null;

    // Singleton
    private static MediaPlayerService instance;
    private MediaPlayerService() {}
    public static MediaPlayerService getInstance() {
        if(instance == null)
            instance = new MediaPlayerService();
        return instance;
    }
    // Control Song
    private int playStatus = PLAY_DEFAULT;
    public int getPlayStatus() { return playStatus; }
    private int pause = UN_PAUSE;
    private boolean trackingTouch = false;
    public void setTrackingTouch(boolean trackingTouch) { this.trackingTouch = trackingTouch; }

    private static float DegImage = 0;
    private static ArrayList<SongDTO> songs;
    private static Gson gson = new Gson();
    private static int position = 0;
    private static MediaPlayer mediaPlayer;
    private static SongAdapter songAdapter;
    public static void setSongAdapter(SongAdapter songAdapter) { MediaPlayerService.songAdapter = songAdapter; }
    private void updateUISongs() { MediaPlayerService.songAdapter.notifyDataSetChanged(); }


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


    public void createMediaPlayer(Context context, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation, SeekBar seekBarSong, TextView textViewStartTime, TextView textViewEndTime, TextView textViewTitleName, TextView textViewArtist) {
        context = imageViewPlayOrPause.getContext();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            if(songs != null) {
                StreamSongDTO stream = gson.fromJson(APIServiceZingmp3.getInstance().getStreaming(songs.get(position).getEncodeId())
                        , StreamSongDTO.class);
                String url = stream.get_128();
                if(url == null || url.contains("\"error\"")) {
                    Toast.makeText(context, "Bài hát đang lỗi", Toast.LENGTH_LONG).show();
                }
                else {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            setTimeTotal(seekBarSong, textViewEndTime);
                            updateTimeStart(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation, textViewTitleName, textViewArtist);
                            mp.start();
                            imageViewRotate.startAnimation(rotateAnimation);
                            pauseSong(seekBarSong, imageViewPlayOrPause, imageViewRotate);
                            playSong(seekBarSong,textViewStartTime,imageViewPlayOrPause,imageViewRotate,rotateAnimation, textViewTitleName, textViewArtist);
                        }
                    });
//                    updateTimeStart(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation);
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
    public void updateTimeStart(SeekBar seekBarSong, TextView textViewStartTime, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation, TextView textViewTitleName, TextView textViewArtist) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!trackingTouch) {
                    if(Utilities.roundingSeconds(seekBarSong.getProgress()) == Utilities.roundingSeconds(seekBarSong.getMax())) {
                        try {
                            MediaPlayerService.getInstance().nextSong(imageViewRotate, textViewTitleName, textViewArtist);
                        } catch (IOException e) {
                            Toast.makeText(context, "Hệ thống phát nhạc lỗi", Toast.LENGTH_LONG).show();
                        }
                    }
                    if(pause == UN_PAUSE) {
                        if(!mediaPlayer.isPlaying()){
                            playSong(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation, textViewTitleName, textViewArtist);
                        }
                    }
                    if (pause == PAUSE) {
                        if (mediaPlayer.isPlaying()) {
                            pauseSong(seekBarSong, imageViewPlayOrPause, imageViewRotate);
                        }
                    }
                    SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
                    textViewStartTime.setText(hourseFormat.format(mediaPlayer.getCurrentPosition()));
                    seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                }
                handler.postDelayed(this, 100);
            }
        },100);
    }
    public void playSong(SeekBar seekBarSong, TextView textViewStartTime, ImageView imageViewPlayOrPause, ImageView imageViewRotate, Animation rotateAnimation, TextView textViewTitleName, TextView textViewArtist) {
        if(mediaPlayer != null){
            pause = UN_PAUSE;
            mediaPlayer.start();
            updateTimeStart(seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation, textViewTitleName, textViewArtist);
            imageViewPlayOrPause.setImageResource(R.drawable.icon_play);
            imageViewRotate.startAnimation(rotateAnimation);
        }
        else {
            pauseSong(seekBarSong, imageViewPlayOrPause, imageViewRotate);
        }
    }
    public void pauseSong(SeekBar seekBarSong, ImageView imageViewPlayOrPause, ImageView imageViewRotate) {
        pause = PAUSE;
        imageViewPlayOrPause.setImageResource(R.drawable.icon_pause);
        long timePlay = seekBarSong.getProgress();
        imageViewRotate.clearAnimation();
        setRotationWithTime(imageViewRotate, timePlay);
        mediaPlayer.pause();
    }
    public void setRotationWithTime(ImageView imageViewRotate, long timePlay) {
        MediaPlayerService.DegImage = calculatorRotation(timePlay);
        imageViewRotate.setRotation(MediaPlayerService.DegImage);
    }
    private float calculatorRotation(long time) {
        float duration = (float)20000;
        float currentDegree = time > duration ? ((time % duration) * 360f) / duration : (time * 360f) / duration;
        return currentDegree;
    }
    public void nextSong(ImageView imageViewRotate, TextView textViewNameSong, TextView textViewArtist) throws IOException {
        if(playStatus == PLAY_DEFAULT) {
            if(position < songs.size() - 1) {
                position += 1;
            }
            else {
                position = 0;
            }
        }
        else if(playStatus == PLAY_RANDOM) {
            randomPosition();
        }
        setSourceUpdateSong();
        updateInfoUI(imageViewRotate, textViewNameSong, textViewArtist);
    }
    public void previousSong(ImageView imageViewRotate, TextView textViewNameSong, TextView textViewArtist) throws IOException {
        if(playStatus == PLAY_DEFAULT) {
            position -= 1;
            if(position < 0) {
                position = songs.size() - 1;
            }
        }
        else if(playStatus == PLAY_RANDOM) {
            randomPosition();
        }
        setSourceUpdateSong();
        updateInfoUI(imageViewRotate, textViewNameSong, textViewArtist);
    }
    private void updateInfoUI(ImageView imageViewRotate, TextView textViewNameSong, TextView textViewArtist) {
        SongDTO song = songs.get(position);
        imageViewRotate.setImageDrawable(Utilities.LoadImageFromWebOperations(song.getThumbnailM()));
        textViewNameSong.setText(song.getTitle());
        textViewArtist.setText(song.getArtistsNames());
        updateUISongs();
    }
    public void setPlayDefault() {
        playStatus = PLAY_DEFAULT;
    }
    public void setPlayRepeat() {
        playStatus = PLAY_REPEAT;
    }
    public void setPlayRandom() {
        playStatus = PLAY_RANDOM;
    }
    private void randomPosition() {
        Random random = new Random();
        int pos;
        while ((pos = random.nextInt(songs.size())) != position) {
            position = pos;
            break;
        }
    }
    private void setSourceUpdateSong() throws IOException {
        StreamSongDTO stream = gson.fromJson(APIServiceZingmp3.getInstance().getStreaming(songs.get(position).getEncodeId())
                , StreamSongDTO.class);
        String url = stream.get_128();
        if(url == null || url.contains("\"error\"")) {
            Toast.makeText(context, "Bài hát đang lỗi", Toast.LENGTH_LONG).show();
        }
        else {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
        }
    }
}

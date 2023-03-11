package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class PlayMusicActivity extends AppCompatActivity {

    ImageView imageViewRotate, imageViewPlayOrPause;
    TextView textViewStartTime, textViewEndTime, textViewTitleName, textViewArtist;
    SeekBar seekBarSong;
    MediaPlayer mediaPlayer;
    Animation rotateAnimation;

    //String url = "https://cdn.pixabay.com/download/audio/2021/12/16/audio_232a4bdedf.mp3";
    //String url = "https://dl179.dlmate15.online/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyZ011ZXh4NjFVQVV1NFU5c1liNWFHRGQ0d1hmSWtvaHFUbk1vMFJyRzN4T3B1dkVUdWlsN2x0V2ppeDVkazU4Zzc3dUt3RmVQMUxIVG5ldmMrQXZ4QmF3aXJBVGZIdVhvNS9TVkpVN1UxemdHQ1Z4ZjNSaGdYNXAzWHc0aCtsZFNzZnBpNEpPTENDMTVKWWhpeVpTYStoZ2JZc2x3Rzh4NTBaOWQ2U3QwLzcwK0Y0djlsNkEzSmFOWlZGeVpQbjJ1VEZxRUpFMGM5TGdoLzMrckwxVnRnbkdhT2hlajkxUFNsVXVLbjhWUUVia25CTnFEam5yLzlqL1haVWRhSWo%3D";
    //String url = "https://dl.sndup.net/s9p8/haylatrangnoi.mp3";
    //String url = "https://dl.sndup.net/xv7t/Hay-La-Trang-Noi.mp3";
    //String url = "https://dl.sndup.net/xv7t/Hay-La-Trang-Noi.mp3";
    //String url = "https://dl.sndup.net/rpp5/Cuoi-Cung-Thi.mp3";
    String url = "https://dl.sndup.net/k2pp/Khuat-Loi_H-Kray.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_circle);
        imageViewRotate         = findViewById(R.id.playmusic_imageView_rotate);
        imageViewPlayOrPause    = findViewById(R.id.playmusic_imageView_iconPlayOrPause);
        textViewStartTime       = findViewById(R.id.playmusic_textView_timeStart);
        textViewEndTime         = findViewById(R.id.playmusic_textView_timeEnd);
        seekBarSong             = findViewById(R.id.playmusic_seekbar);
        textViewArtist          = findViewById(R.id.playmusic_textView_artist);
        textViewTitleName       = findViewById(R.id.playmusic_textView_titleMusic);

//        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
//        metaRetriever.setDataSource(url);
//        String artist =  metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
//        String title = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
//        textViewTitleName.setText(title);
//        textViewArtist.setText(artist);
//        System.out.println(artist + ", " + title);
//        System.out.println(metaRetriever);

        //imageViewRotate.startAnimation(rotateAnimation);
        createMediaPlayer();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("release");
                //mp.release();
                //pauseSong();
                //createMediaPlayer();
            }
        });

        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imageViewPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    pauseSong();
                }
                else {
                    playSong();
                }
            }
        });
    }

    public void createMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    setTimeTotal();
                    imageViewRotate.startAnimation(rotateAnimation);
                    mp.start();
                }
            });
            updateTimeStart();
        } catch (IOException e) {
            Toast.makeText(this, "Bạn không cấp quyền nên không thể thực hiện.", Toast.LENGTH_SHORT).show();
        }
    }
    public void setTimeTotal() {
        SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
        textViewEndTime.setText(hourseFormat.format(mediaPlayer.getDuration()));
        seekBarSong.setMax(mediaPlayer.getDuration());
    }
    public void updateTimeStart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat hourseFormat = new SimpleDateFormat("mm:ss");
                textViewStartTime.setText(hourseFormat.format(mediaPlayer.getCurrentPosition()));
                seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                if(seekBarSong.getMax() == seekBarSong.getProgress()) {
                    createMediaPlayer();
                }
                handler.postDelayed(this, 500);
            }
        },500);
    }
    public void playSong() {
        imageViewPlayOrPause.setImageResource(R.drawable.icon_play);
        imageViewRotate.startAnimation(rotateAnimation);
        mediaPlayer.start();
    }
    public void pauseSong() {
        imageViewPlayOrPause.setImageResource(R.drawable.icon_pause);
        imageViewRotate.clearAnimation();
        mediaPlayer.pause();
    }

//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            System.out.println("vô");
//            InputStream is = getAssets().open("data.json");
//            System.out.println("thành công");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            System.out.println("close");
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
}
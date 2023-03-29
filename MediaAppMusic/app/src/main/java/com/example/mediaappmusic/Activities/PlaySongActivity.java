package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mediaappmusic.APIs.IAPIData;
import com.example.mediaappmusic.Adapters.ViewPagerPlaySong;
import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.Fragments.PlaySongFragment;
import com.example.mediaappmusic.Fragments.SongsFragment;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.MediaPlayerService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaySongActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String idSong = (String) bundle.get("IDSong");



        viewPager       = findViewById(R.id.playSong_viewPager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(PlaySongFragment.newInstance(new SongDTO()));
        fragments.add(SongsFragment.newInstance(MediaPlayerService.getSongs()));

        ViewPagerPlaySong viewPagerPlaySongAdapter = new ViewPagerPlaySong(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerPlaySongAdapter);


//        IAPIData.iApiService.getFullInfo(idSong).enqueue(new Callback<SongDTO>() {
//            @Override
//            public void onResponse(Call<SongDTO> call, Response<SongDTO> response) {
//                SongDTO songDTO = response.body();
//                imageViewRotate.setImageDrawable(Utilities.LoadImageFromWebOperations(songDTO.getThumbnailM()));
//                textViewArtist.setText(songDTO.getArtistsNames());
//                textViewTitleName.setText(songDTO.getTitle());
//                mediaPlayer = MediaPlayerService.createMediaPlayer(PlaySongActivity.this, songDTO.getStreaming().get_128(), imageViewPlayOrPause, imageViewRotate, rotateAnimation,
//                        seekBarSong, textViewStartTime, textViewEndTime);
//
//                if(mediaPlayer != null) {
//
//                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mediaPlayer) {
//                            System.out.println("release");
//
//                        }
//                    });
//
//
//
//                    seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        @Override
//                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                            if(seekBar.getProgress() == seekBar.getMax()) {
//                                MediaPlayerService.pauseSong(mediaPlayer, imageViewPlayOrPause, imageViewRotate);
//                            }
//                        }
//
//                        @Override
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//                        }
//
//                        @Override
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//                            mediaPlayer.seekTo(seekBar.getProgress());
//                        }
//                    });
//
//                    imageViewPlayOrPause.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if(mediaPlayer.isPlaying()) {
//                                MediaPlayerService.pauseSong(mediaPlayer, imageViewPlayOrPause, imageViewRotate);
//                            }
//                            else {
//                                MediaPlayerService.playSong(mediaPlayer, seekBarSong, textViewStartTime, imageViewPlayOrPause, imageViewRotate, rotateAnimation);
//                            }
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SongDTO> call, Throwable t) {
//
//            }
//        });



    }
}
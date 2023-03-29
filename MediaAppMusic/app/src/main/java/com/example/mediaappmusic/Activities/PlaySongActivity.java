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



        viewPager       = findViewById(R.id.playSong_viewPager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(PlaySongFragment.newInstance());
        fragments.add(SongsFragment.newInstance(MediaPlayerService.getSongs()));

        ViewPagerPlaySong viewPagerPlaySongAdapter = new ViewPagerPlaySong(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerPlaySongAdapter);
    }
}
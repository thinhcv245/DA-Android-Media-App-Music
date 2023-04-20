package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mediaappmusic.Adapters.ViewPagerPlaySong;
import com.example.mediaappmusic.Fragments.PlaySongFragment;
import com.example.mediaappmusic.Fragments.SongsFragment;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.MediaPlayerService;

import java.util.ArrayList;

public class PlaySongActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);



        viewPager       = findViewById(R.id.playSong_viewPager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(PlaySongFragment.newInstance(PlaySongActivity.this));
        fragments.add(SongsFragment.newInstance(MediaPlayerService.getSongs()));

        ViewPagerPlaySong viewPagerPlaySongAdapter = new ViewPagerPlaySong(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerPlaySongAdapter);
    }
}
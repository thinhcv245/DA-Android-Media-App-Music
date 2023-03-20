package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mediaappmusic.APIs.IApiService;
import com.example.mediaappmusic.Adapters.SongAdapter;
import com.example.mediaappmusic.Models.Artist;
import com.example.mediaappmusic.Models.PlayList;
import com.example.mediaappmusic.Models.Song;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListActivity extends AppCompatActivity {

    RecyclerView playList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        playList        = findViewById(R.id.playlist_recyclerView_list);

        ArrayList<Song> songArrayList = new ArrayList<>();
        songArrayList.add(new Song("1","Cuoi-Cung-Thi_Jack.jpg", "Cuối cùng thì", "Jack", null));
        songArrayList.add(new Song("1","Khuat-Loi_H-Kray.jpg", "Khuất lối", "H-Kay", null));
        SongAdapter adapter = new SongAdapter(this, songArrayList);
        playList.setAdapter(adapter);
        playList.setLayoutManager(new LinearLayoutManager(this));

//        ArrayList<Artist> items = new ArrayList<>();
//        items.add(new Artist("ida1", "a1", "a1.jpg", "j"));
//        items.add(new Artist("ida2", "a2", "a2.jpg", "j2"));
//        Song song = new Song("jjj", "mới thêm", "Thịnh",
//                "jjjj.jpg", items);
//
//        Gson gson = new Gson();
//        Log.e("String Json", gson.toJson(song));


    }

}
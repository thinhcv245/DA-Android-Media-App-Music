package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mediaappmusic.Adapters.SongAdapter;
import com.example.mediaappmusic.DTO.SongDTO;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {

    RecyclerView playList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        playList        = findViewById(R.id.playlist_recyclerView_list);

//        ArrayList<SongDTO> songArrayList = new ArrayList<>();
//        songArrayList.add(new SongDTO("1","Cuoi-Cung-Thi_Jack.jpg", "Cuối cùng thì", "Jack", null));
//        songArrayList.add(new SongDTO("1","Khuat-Loi_H-Kray.jpg", "Khuất lối", "H-Kay", null));
//        SongAdapter adapter = new SongAdapter(this, songArrayList);
//        playList.setAdapter(adapter);
//        playList.setLayoutManager(new LinearLayoutManager(this));

//        ArrayList<ArtistDTO> items = new ArrayList<>();
//        items.add(new ArtistDTO("ida1", "a1", "a1.jpg", "j"));
//        items.add(new ArtistDTO("ida2", "a2", "a2.jpg", "j2"));
//        SongDTO song = new SongDTO("jjj", "mới thêm", "Thịnh",
//                "jjjj.jpg", items);
//
//        Gson gson = new Gson();
//        Log.e("String Json", gson.toJson(song));


    }

}
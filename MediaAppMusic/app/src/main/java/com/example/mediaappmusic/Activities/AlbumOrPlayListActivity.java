package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mediaappmusic.Adapters.SongAdapter;
import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.APIServiceZingmp3;
import com.example.mediaappmusic.Services.MediaPlayerService;
import com.example.mediaappmusic.Services.ServiceBackground;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AlbumOrPlayListActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ImageView imageViewAlbum;
    RecyclerView recyclerViewListSong;
    String idAlbum;
    //String nameObjectDTO;
    AlbumDTO albumDTO;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_or_play_list);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        idAlbum = (String) bundle.get("IDAlbum");
        Log.d("idAlbum", idAlbum);
        //nameObjectDTO = (String) bundle.get("nameObjectDTO");

        toolbar                     = findViewById(R.id.albumOrPlayList_toolBar);
        imageViewAlbum              = findViewById(R.id.albumOrPlayList_imageView_album);
        collapsingToolbarLayout     = findViewById(R.id.albumOrPlayList_collapsingToolbarLayout);
        recyclerViewListSong        = findViewById(R.id.albumOrPlayList_recyclerView_listSong);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        callGetDetails();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void setValue(String name, String link) {
        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setBackground(Utilities.LoadImageFromWebOperations(link));
        imageViewAlbum.setImageDrawable(Utilities.LoadImageFromWebOperations(link));
    }


    private void callGetDetails() {
        albumDTO = ServiceBackground.getAlbum(idAlbum);

        if(albumDTO != null) {
            MediaPlayerService.setSongs(albumDTO.getSong().getItems());

            setValue(albumDTO.getTitle(), albumDTO.getThumbnail());

            SongAdapter adapter = new SongAdapter(AlbumOrPlayListActivity.this, albumDTO.getSong().getItems());
            recyclerViewListSong.setAdapter(adapter);
            recyclerViewListSong.setLayoutManager(new LinearLayoutManager(AlbumOrPlayListActivity.this));
        }
        else {
            Toast.makeText(this, "Load data error", Toast.LENGTH_LONG).show();
        }
    }
}
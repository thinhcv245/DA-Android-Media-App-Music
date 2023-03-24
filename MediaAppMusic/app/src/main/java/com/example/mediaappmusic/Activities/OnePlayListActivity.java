package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mediaappmusic.Adapters.AlbumAdapter;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.R;

public class OnePlayListActivity extends AppCompatActivity {

    ObjectDTO objectDTO;
    Toolbar toolbar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_play_list);

        toolbar                 = findViewById(R.id.onePlayList_toolBar);
        recyclerView            = findViewById(R.id.onePlayList_recyclerView);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        objectDTO = (ObjectDTO) bundle.getSerializable("objectDTO");
        if(objectDTO != null) {
            Toast.makeText(this, objectDTO.getTitle(), Toast.LENGTH_SHORT).show();
            actionBar.setTitle(objectDTO.getTitle());
            AlbumAdapter albumAdapter = new AlbumAdapter(this, objectDTO.getItems());
            recyclerView.setAdapter(albumAdapter);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
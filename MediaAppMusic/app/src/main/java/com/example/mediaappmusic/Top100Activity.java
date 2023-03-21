package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.APIs.IApiService;
import com.example.mediaappmusic.Adapters.AlbumAdapter;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.Models.Album;
import com.example.mediaappmusic.Models.PlayList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top100Activity extends AppCompatActivity {

    ImageView imageViewBanner;
    ArrayList<RecyclerView> recyclerViews;
    ArrayList<TextView> textViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top100);

        recyclerViews   = new ArrayList<>();
        textViews       = new ArrayList<>();
        imageViewBanner         = findViewById(R.id.top100_imageView_banner);

        for(int i = 0; i < 6; i++) {
            try{
                int id = getResources().getIdentifier("top100_recyclerView_recycle" + i, "id", this.getPackageName());
                recyclerViews.add(findViewById(id));
                int idText = getResources().getIdentifier("top100_textView_title" + i, "id",this.getPackageName());
                textViews.add(findViewById(idText));
            }
            catch (Exception e) {
                System.out.println("Error " + i);
            }
        }

        Drawable drawable = Utilities.LoadImageFromAssets(Top100Activity.this, "banner.jpg");
        if(drawable != null) {
            imageViewBanner.setImageDrawable(drawable);
        }


        CallPlayListTop100();
    }
    public void CallPlayListTop100() {
        IApiService.iApiService.getPlayListTop100().enqueue(new Callback<ArrayList<PlayList>>() {
            @Override
            public void onResponse(Call<ArrayList<PlayList>> call, Response<ArrayList<PlayList>> response) {
                if (response.isSuccessful()) {
                    ArrayList<PlayList> playLists = response.body();

                    for(int i = 0; i < playLists.size(); i++) {
                        AlbumAdapter albumAdapter = new AlbumAdapter(Top100Activity.this, playLists.get(i).getItems());
                        RecyclerView recyclerView = recyclerViews.get(i);
                        if(recyclerView != null) {
                            recyclerView.setAdapter(albumAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(Top100Activity.this, LinearLayoutManager.HORIZONTAL, false));
                        }
                        TextView textView = textViews.get(i);
                        textView.setText(playLists.get(i).getTitle());
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PlayList>> call, Throwable t) {
                Toast.makeText(Top100Activity.this, "Call API Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
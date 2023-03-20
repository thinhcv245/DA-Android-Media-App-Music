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

    RecyclerView recyclerView0, recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5;
    TextView textViewTitle0, textViewTitle1, textViewTitle2, textViewTitle3, textViewTitle4, textViewTitle5;
    ImageView imageViewBanner;

//    ArrayList<PlayList> playLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top100);

        imageViewBanner         = findViewById(R.id.top100_imageView_banner);
        recyclerView0           = findViewById(R.id.top100_recyclerView_recycle0);
        recyclerView1           = findViewById(R.id.top100_recyclerView_recycle1);
        recyclerView2           = findViewById(R.id.top100_recyclerView_recycle2);
        recyclerView3           = findViewById(R.id.top100_recyclerView_recycle3);
        recyclerView4           = findViewById(R.id.top100_recyclerView_recycle4);
        recyclerView5           = findViewById(R.id.top100_recyclerView_recycle5);
        textViewTitle0          = findViewById(R.id.top100_textView_title0);
        textViewTitle1          = findViewById(R.id.top100_textView_title1);
        textViewTitle2          = findViewById(R.id.top100_textView_title2);
        textViewTitle3          = findViewById(R.id.top100_textView_title3);
        textViewTitle4          = findViewById(R.id.top100_textView_title4);
        textViewTitle5          = findViewById(R.id.top100_textView_title5);
        getResources().getIdentifier()

        imageViewBanner.setImageDrawable(Utilities.LoadImageFromAssets(Top100Activity.this, "banner.jpg"));


        CallPlayListTop100();
//        if(playLists != null) {
//            System.out.println(playLists.size());
//        }
//        else {
//            System.out.println("null");
//        }


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        albums = new ArrayList<>();
//        AlbumAdapter albumAdapter = new AlbumAdapter(this, albums);
//        recyclerViewNoiBat.setAdapter(albumAdapter);
//        recyclerViewNoiBat.setLayoutManager(linearLayoutManager);
    }
    public void CallPlayListTop100() {
        IApiService.iApiService.getPlayListTop100().enqueue(new Callback<ArrayList<PlayList>>() {
            @Override
            public void onResponse(Call<ArrayList<PlayList>> call, Response<ArrayList<PlayList>> response) {
                if (response.isSuccessful()) {
                    ArrayList<PlayList> playLists = response.body();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Top100Activity.this, LinearLayoutManager.HORIZONTAL, false);

                    ArrayList<Album> albums0 = playLists.get(0).getItems();
                    AlbumAdapter albumAdapter0 = new AlbumAdapter(Top100Activity.this, albums0);
                    recyclerView0.setAdapter(albumAdapter0);
                    recyclerView0.setLayoutManager(linearLayoutManager);

                    ArrayList<Album> albums1 = playLists.get(1).getItems();
                    AlbumAdapter albumAdapter1 = new AlbumAdapter(Top100Activity.this, albums1);
                    recyclerView1.setAdapter(albumAdapter1);
                    recyclerView1.setLayoutManager(new LinearLayoutManager(Top100Activity.this, LinearLayoutManager.HORIZONTAL, false));

//                    ArrayList<Album> albums2 = playLists.get(2).getItems();
//                    AlbumAdapter albumAdapter2 = new AlbumAdapter(Top100Activity.this, albums2);
//                    recyclerView2.setAdapter(albumAdapter2);
//                    recyclerView2.setLayoutManager(linearLayoutManager);
//
//                    ArrayList<Album> albums3 = playLists.get(3).getItems();
//                    AlbumAdapter albumAdapter3 = new AlbumAdapter(Top100Activity.this, albums3);
//                    recyclerView3.setAdapter(albumAdapter3);
//                    recyclerView3.setLayoutManager(linearLayoutManager);

//                    ArrayList<Album> albums4 = playLists.get(4).getItems();
//                    AlbumAdapter albumAdapter4 = new AlbumAdapter(Top100Activity.this, albums4);
//                    recyclerView0.setAdapter(albumAdapter4);
//                    recyclerView0.setLayoutManager(linearLayoutManager);
//
//                    ArrayList<Album> albums5 = playLists.get(5).getItems();
//                    AlbumAdapter albumAdapter5 = new AlbumAdapter(Top100Activity.this, albums5);
//                    recyclerView0.setAdapter(albumAdapter5);
//                    recyclerView0.setLayoutManager(linearLayoutManager);


//                    for (PlayList item:playLists) {
//                        System.out.println(item.getTitle().compareToIgnoreCase("Nổi bật") == 0);
//                        if(item.getTitle().compareToIgnoreCase("Nổi bật") == 0) {
//                            albums = item.getItems();
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Top100Activity.this, LinearLayoutManager.HORIZONTAL, false);
//                            AlbumAdapter albumAdapter = new AlbumAdapter(Top100Activity.this, albums);
//                            recyclerViewNoiBat.setAdapter(albumAdapter);
//                            recyclerViewNoiBat.setLayoutManager(linearLayoutManager);
//                            return;
//                        }
//                        System.out.println(item.getTitle());
//                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PlayList>> call, Throwable t) {
                Toast.makeText(Top100Activity.this, "Call API Error", Toast.LENGTH_LONG).show();
                System.out.println("Error");
            }
        });
    }
}
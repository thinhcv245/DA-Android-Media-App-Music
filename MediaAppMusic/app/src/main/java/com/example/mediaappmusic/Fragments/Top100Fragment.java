package com.example.mediaappmusic.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.APIs.IApiService;
import com.example.mediaappmusic.Adapters.AlbumAdapter;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.Models.PlayList;
import com.example.mediaappmusic.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top100Fragment extends Fragment {
    private static final String ARG_PARAM1 = "TITLE";
    private String _title;

    ImageView imageViewBanner;
    ArrayList<RecyclerView> recyclerViews;
    ArrayList<TextView> textViews;
    public Top100Fragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _title = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_top100, container, false);
        recyclerViews       = new ArrayList<>();
        textViews           = new ArrayList<>();

        imageViewBanner     = layout.findViewById(R.id.top100Fragment_imageView_banner);

        for(int i = 0; i < 6; i++) {
            try{
                int id = getResources().getIdentifier("top100Fragment_recyclerView_recycle" + i, "id", getContext().getPackageName());
                System.out.println("ID: " + id);
                recyclerViews.add(layout.findViewById(id));
                int idText = getResources().getIdentifier("top100Fragment_textView_title" + i, "id",getContext().getPackageName());
                textViews.add(layout.findViewById(idText));
            }
            catch (Exception e) {
                System.out.println("Error " + i);
            }
        }

        Drawable drawable = Utilities.LoadImageFromAssets(getContext(), "banner.jpg");
        if(drawable != null) {
            imageViewBanner.setImageDrawable(drawable);
        }


        CallPlayListTop100();
        return layout;
    }

    private void CallPlayListTop100() {
        IApiService.iApiService.getPlayListTop100().enqueue(new Callback<ArrayList<PlayList>>() {
            @Override
            public void onResponse(Call<ArrayList<PlayList>> call, Response<ArrayList<PlayList>> response) {
                if (response.isSuccessful()) {
                    ArrayList<PlayList> playLists = response.body();
                    for(int i = 0; i < playLists.size(); i++) {
                        AlbumAdapter albumAdapter = new AlbumAdapter(getContext(), playLists.get(i).getItems());
                        System.out.println("Tên album: " + playLists.get(i).getItems().get(0).getTitle());
                        RecyclerView recyclerView = recyclerViews.get(i);
                        System.out.println("check: " + String.valueOf(recyclerView != null));
                        if(recyclerView != null) {
                            recyclerView.setAdapter(albumAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        }
                        TextView textView = textViews.get(i);
                        textView.setText(playLists.get(i).getTitle());
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PlayList>> call, Throwable t) {
                Toast.makeText(getContext(), "Call API Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
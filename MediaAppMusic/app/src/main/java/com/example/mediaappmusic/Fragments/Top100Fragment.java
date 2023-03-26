package com.example.mediaappmusic.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.APIs.IAPIData;
import com.example.mediaappmusic.Adapters.AlbumAdapter;
import com.example.mediaappmusic.Adapters.ObjectDTOAdapter;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top100Fragment extends Fragment {
    ImageView imageViewBanner;
    LinearLayout linearLayoutLoadAPI;
    ArrayList<ObjectDTO> objectDTOS;
    RecyclerView recyclerViewObjectDTO;
    public Top100Fragment() {
        // Required empty public constructor
    }

    public static Top100Fragment newInstance() {
        Top100Fragment fragment = new Top100Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_top100, container, false);

        imageViewBanner         = layout.findViewById(R.id.top100Fragment_imageView_banner);
        recyclerViewObjectDTO   = layout.findViewById(R.id.top100Fragment_recyclerView);
        linearLayoutLoadAPI     = layout.findViewById(R.id.top100Fragment_linearLayout_loadAPI);
        linearLayoutLoadAPI.setVisibility(View.GONE);

        Drawable drawable = Utilities.LoadImageFromAssets(getContext(), "banner.jpg");
        if(drawable != null) {
            imageViewBanner.setImageDrawable(drawable);
        }

        CallPlayListTop100();
        return layout;
    }

    private void pushDataToView(ArrayList<ObjectDTO> objectDTOS) {
        ObjectDTOAdapter objectDTOAdapter = new ObjectDTOAdapter(getContext(), objectDTOS);
        recyclerViewObjectDTO.setAdapter(objectDTOAdapter);
        recyclerViewObjectDTO.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void CallPlayListTop100() {
        linearLayoutLoadAPI.setVisibility(View.VISIBLE);
        IAPIData.iApiService.getPlayListTop100().enqueue(new Callback<ArrayList<ObjectDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<ObjectDTO>> call, Response<ArrayList<ObjectDTO>> response) {
                if (response.isSuccessful()) {
                    objectDTOS = response.body();
                    pushDataToView(objectDTOS);
                }
                linearLayoutLoadAPI.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ArrayList<ObjectDTO>> call, Throwable t) {
                IAPIData.iApiService.getPlayListTop100().enqueue(new Callback<ArrayList<ObjectDTO>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ObjectDTO>> call, Response<ArrayList<ObjectDTO>> response) {
                        if (response.isSuccessful()) {
                            objectDTOS = response.body();
                            pushDataToView(objectDTOS);
                        }
                        linearLayoutLoadAPI.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ObjectDTO>> call, Throwable t) {
                        linearLayoutLoadAPI.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "API Error", Toast.LENGTH_LONG).show();
                    }
                });
//                Toast.makeText(getContext(), "Call API Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
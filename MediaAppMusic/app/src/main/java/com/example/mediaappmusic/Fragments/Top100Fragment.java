package com.example.mediaappmusic.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.APIs.IAPIData;
import com.example.mediaappmusic.Adapters.AlbumAdapter;
import com.example.mediaappmusic.Adapters.ObjectDTOAdapter;
import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.APIService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top100Fragment extends Fragment {
    ImageView imageViewBanner;
    LinearLayout linearLayoutLoadAPI;
    FrameLayout frameLayoutLoading;
    ArrayList<ObjectDTO> objectDTOS;
    RecyclerView recyclerViewObjectDTO;
    Gson gson = new Gson();
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
        frameLayoutLoading      = layout.findViewById(R.id.top100Fragment_frameLayout_loading);
        frameLayoutLoading.setVisibility(View.GONE);

        Drawable drawable = Utilities.LoadImageFromAssets(getContext(), "banner.jpg");
        if(drawable != null) {
            imageViewBanner.setImageDrawable(drawable);
        }
        CallPlayListTop100();
        return layout;
    }

    private void pushDataToView(ArrayList<ObjectDTO> objectDTOS) {
        ObjectDTOAdapter objectDTOAdapter = new ObjectDTOAdapter(getContext(), objectDTOS, frameLayoutLoading);
        recyclerViewObjectDTO.setAdapter(objectDTOAdapter);
        recyclerViewObjectDTO.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void CallPlayListTop100() {
        frameLayoutLoading.setVisibility(View.VISIBLE);
        String api = APIService.getInstance().getPlayListTop100();
        try{
            if(!api.contains("err")) {
                objectDTOS = gson.fromJson(api, new TypeToken<ArrayList<ObjectDTO>>(){}.getType());
                pushDataToView(objectDTOS);
            }
            else {
                Toast.makeText(getContext(), "Call API Error", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Log.e("Error call API: ", e.getMessage());
        }
        frameLayoutLoading.setVisibility(View.GONE);
    }
}
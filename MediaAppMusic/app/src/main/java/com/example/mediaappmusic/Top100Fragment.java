package com.example.mediaappmusic;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaappmusic.APIs.IAPIData;
import com.example.mediaappmusic.Adapters.AlbumAdapter;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.Fragments.HomeFragment;
import com.example.mediaappmusic.Helpers.Utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Top100Fragment extends Fragment {
    ImageView imageViewBanner;
    ArrayList<RecyclerView> recyclerViews;
    ArrayList<TextView> textViews;
    LinearLayout linearLayoutLoadAPI;
    ArrayList<ObjectDTO> objectDTOS;
    public Top100Fragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        recyclerViews       = new ArrayList<>();
        textViews           = new ArrayList<>();

        imageViewBanner     = layout.findViewById(R.id.top100Fragment_imageView_banner);
        linearLayoutLoadAPI  = layout.findViewById(R.id.top100Fragment_linearLayout_loadAPI);
        linearLayoutLoadAPI.setVisibility(View.GONE);

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

    private void pushDataToView(ArrayList<ObjectDTO> objectDTOS) {
        for(int i = 0; i < objectDTOS.size(); i++) {
            AlbumAdapter albumAdapter = new AlbumAdapter(getContext(), objectDTOS.get(i).getItems());
            RecyclerView recyclerView = recyclerViews.get(i);
            if(recyclerView != null) {
                recyclerView.setAdapter(albumAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
            TextView textView = textViews.get(i);
            textView.setText(objectDTOS.get(i).getTitle());
        }
    }
    private void CallPlayListTop100() {
        linearLayoutLoadAPI.setVisibility(View.VISIBLE);
        IAPIData.iApiService.getPlayListTop100().enqueue(new Callback<ArrayList<ObjectDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<ObjectDTO>> call, Response<ArrayList<ObjectDTO>> response) {

                if (response.isSuccessful()) {
                    objectDTOS = response.body();
                    pushDataToView(objectDTOS);
//                    for(int i = 0; i < objectDTOS.size(); i++) {
//                        AlbumAdapter albumAdapter = new AlbumAdapter(getContext(), objectDTOS.get(i).getItems());
//                        System.out.println("Tên album: " + objectDTOS.get(i).getItems().get(0).getTitle());
//                        RecyclerView recyclerView = recyclerViews.get(i);
//                        System.out.println("check: " + String.valueOf(recyclerView != null));
//                        if(recyclerView != null) {
//                            recyclerView.setAdapter(albumAdapter);
//                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//                        }
//                        TextView textView = textViews.get(i);
//                        textView.setText(objectDTOS.get(i).getTitle());
//                    }
                }
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
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ObjectDTO>> call, Throwable t) {
                        Toast.makeText(getContext(), "Call API Error", Toast.LENGTH_LONG).show();
                    }
                });
//                Toast.makeText(getContext(), "Call API Error", Toast.LENGTH_LONG).show();
            }
        });
        linearLayoutLoadAPI.setVisibility(View.GONE);
    }
}
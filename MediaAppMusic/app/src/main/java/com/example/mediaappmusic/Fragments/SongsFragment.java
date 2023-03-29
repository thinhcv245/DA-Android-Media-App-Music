package com.example.mediaappmusic.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaappmusic.Adapters.SongAdapter;
import com.example.mediaappmusic.DTO.SongDTO;
import com.example.mediaappmusic.R;

import java.util.ArrayList;

public class SongsFragment extends Fragment {
    private static final String ARG_PARAMSONGS = "paramSongs";

    private ArrayList<SongDTO> songs;

    public SongsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SongsFragment newInstance(ArrayList<SongDTO> songs) {
        SongsFragment fragment = new SongsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAMSONGS, songs);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            songs = (ArrayList<SongDTO>) getArguments().getSerializable(ARG_PARAMSONGS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_songs, container, false);
        RecyclerView recyclerView = layout.findViewById(R.id.songsFragment_recyclerView);
        SongAdapter adapter = new SongAdapter(getContext(), songs, R.color.white);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return layout;
    }
}
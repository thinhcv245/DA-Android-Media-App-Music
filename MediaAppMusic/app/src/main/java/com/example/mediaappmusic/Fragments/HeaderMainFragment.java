package com.example.mediaappmusic.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaappmusic.R;

public class HeaderMainFragment extends Fragment {
    public HeaderMainFragment() {
    }

    public static HeaderMainFragment newInstance() {
        HeaderMainFragment fragment = new HeaderMainFragment();
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
        View layout = inflater.inflate(R.layout.fragment_header_main, container, false);
        return layout;
    }
}
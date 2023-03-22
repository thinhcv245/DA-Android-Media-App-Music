package com.example.mediaappmusic.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mediaappmusic.Fragments.HomeFragment;
import com.example.mediaappmusic.Fragments.Top100Fragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> items;

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        items = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new Top100Fragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return items.size();
    }
}

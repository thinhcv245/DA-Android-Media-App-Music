package com.example.mediaappmusic.Models;

import java.util.ArrayList;

public class PlayList {
    private String title;
    private ArrayList<Album> items;

    public PlayList(String title, ArrayList<Album> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Album> getItems() {
        return items;
    }

    public void setItems(ArrayList<Album> items) {
        this.items = items;
    }
}

package com.example.mediaappmusic.DTO;

import java.util.ArrayList;

public class SongInAlbum {
    private ArrayList<SongDTO> items;

    public SongInAlbum(ArrayList<SongDTO> items) {
        this.items = items;
    }

    public ArrayList<SongDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<SongDTO> items) {
        this.items = items;
    }
}

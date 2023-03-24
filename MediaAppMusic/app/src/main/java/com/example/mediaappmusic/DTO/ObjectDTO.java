package com.example.mediaappmusic.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectDTO implements Serializable {
    private String title;
    private ArrayList<AlbumDTO> items;

    public ObjectDTO(String title, ArrayList<AlbumDTO> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<AlbumDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<AlbumDTO> items) {
        this.items = items;
    }
}

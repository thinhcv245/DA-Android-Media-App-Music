package com.example.mediaappmusic.DTO;

import java.io.Serializable;

public class ArtistDTO implements Serializable {
    private String id;
    private String name;

    //link image
    private String thumbnail;
    private String playlistId;

    public ArtistDTO(String id, String name, String thumbnail, String playlistId) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.playlistId = playlistId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}

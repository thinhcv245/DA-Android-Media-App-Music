package com.example.mediaappmusic.Models;

import java.util.ArrayList;

public class Album {
    private String encodeId;
    private String title;
    private String thumbnail;
    private String sortDescription;
    private String artistsNames;
    private ArrayList<Artist> artists;

    public Album(String encodeId, String title, String thumbnail, String sortDescription, String artistsNames, ArrayList<Artist> artists) {
        this.encodeId = encodeId;
        this.title = title;
        this.thumbnail = thumbnail;
        this.sortDescription = sortDescription;
        this.artistsNames = artistsNames;
        this.artists = artists;
    }

    public String getEncodeId() {
        return encodeId;
    }

    public void setEncodeId(String encodeId) {
        this.encodeId = encodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }
}

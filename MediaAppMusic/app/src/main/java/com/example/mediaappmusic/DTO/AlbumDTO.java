package com.example.mediaappmusic.DTO;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;

public class AlbumDTO implements Serializable {
    private String encodeId;
    private String title;
    private String thumbnail;
    private String sortDescription;
    private String artistsNames;
    private ArrayList<ArtistDTO> artists;

    public SongInAlbum getSong() {
        return song;
    }

    public void setSong(SongInAlbum song) {
        this.song = song;
    }

    private SongInAlbum song;

    public AlbumDTO(String encodeId, String title, String thumbnail, String sortDescription, String artistsNames, ArrayList<ArtistDTO> artists) {
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

    public ArrayList<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<ArtistDTO> artists) {
        this.artists = artists;
    }
}

package com.example.mediaappmusic.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class SongDTO implements Serializable {
    private String encodeId;
    private String title;
    private String artistsNames;

    //link image
    private String thumbnailM;
    private ArrayList<ArtistDTO> artists;

    public SongDTO(String encodeId, String title, String artistsNames, String thumbnailM, ArrayList<ArtistDTO> artists) {
        this.encodeId = encodeId;
        this.title = title;
        this.artistsNames = artistsNames;
        this.thumbnailM = thumbnailM;
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

    public String getArtistsNames() {
        return artistsNames;
    }

    public void setArtistsNames(String artistsNames) {
        this.artistsNames = artistsNames;
    }

    public String getThumbnailM() {
        return thumbnailM;
    }

    public void setThumbnailM(String thumbnailM) {
        this.thumbnailM = thumbnailM;
    }

    public ArrayList<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<ArtistDTO> artists) {
        this.artists = artists;
    }
}

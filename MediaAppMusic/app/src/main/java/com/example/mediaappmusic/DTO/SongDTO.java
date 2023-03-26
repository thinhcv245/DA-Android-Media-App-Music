package com.example.mediaappmusic.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SongDTO implements Serializable {
    private String encodeId;
    private String title;
    private String artistsNames;

    //link image
    private String thumbnailM;
    private ArrayList<ArtistDTO> artists;
    private StreamSongDTO streaming;

    public SongDTO(String encodeId, String title, String artistsNames, String thumbnailM, ArrayList<ArtistDTO> artists, StreamSongDTO streaming) {
        this.encodeId = encodeId;
        this.title = title;
        this.artistsNames = artistsNames;
        this.thumbnailM = thumbnailM;
        this.artists = artists;
        this.streaming = streaming;
    }

    public StreamSongDTO getStreaming() {
        return streaming;
    }

    public void setStreaming(StreamSongDTO streaming) {
        this.streaming = streaming;
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


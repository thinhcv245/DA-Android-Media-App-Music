package com.example.mediaappmusic.DTO;

import com.google.gson.annotations.SerializedName;

public class StreamSongDTO {

    @SerializedName("128")
    private String _128;

    public StreamSongDTO(String _128) {
        this._128 = _128;
    }

    public String get_128() {
        return _128;
    }

    public void set_128(String _128) {
        this._128 = _128;
    }
}

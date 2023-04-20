package com.example.mediaappmusic.APIs;

import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.example.mediaappmusic.DTO.SongDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIZingmp3 {
    public static String BASE_URL = "https://zingmp3api-dvn.onrender.com/";
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    IAPIZingmp3 iApiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IAPIZingmp3.class);
    @GET("top100")
    Call<ArrayList<ObjectDTO>> getPlayListTop100();

    @GET("getDetailPlaylist/{id}")
    Call<AlbumDTO> getDetailsAlbumOrPlayList(@Path("id") String id);

    @GET("getFullInfo/{id}")
    Call<SongDTO> getFullInfo(@Path("id") String id);
}

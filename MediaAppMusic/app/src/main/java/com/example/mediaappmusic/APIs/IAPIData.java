package com.example.mediaappmusic.APIs;

import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIData {
    public static String BASE_URL = "https://zingmp3api-dvn.onrender.com/";
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    IAPIData iApiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IAPIData.class);
    @GET("top100")
    Call<ArrayList<ObjectDTO>> getPlayListTop100();

    @GET("getDetailPlaylist/{id}")
    Call<AlbumDTO> getDetailsAlbumOrPlayList(@Path("id") String id);
}

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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAPIMusicApp {
    public static String BASE_URL = "https://music-cvtpro.000webhostapp.com/Server/";
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    IAPIMusicApp iapiMusicApp = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IAPIMusicApp.class);
    @FormUrlEncoded
    @POST("Test.php")
    Call<String> test(@Field("id") String id);

    @GET("getAccountOnAndroid.php")
    Call<String> get();
}

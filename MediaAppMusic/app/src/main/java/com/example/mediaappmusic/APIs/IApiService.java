package com.example.mediaappmusic.APIs;

import com.example.mediaappmusic.Models.PlayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface IApiService {
    public static String BASE_URL = "https://zingmp3api-dvn.onrender.com/";
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    IApiService iApiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IApiService.class);

    @GET("top100")
    Call<ArrayList<PlayList>> getPlayListTop100();
}

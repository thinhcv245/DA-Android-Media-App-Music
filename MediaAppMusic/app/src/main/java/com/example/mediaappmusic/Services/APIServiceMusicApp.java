package com.example.mediaappmusic.Services;

import android.util.Log;

import com.example.mediaappmusic.Helpers.HttpGetRequest;
import com.example.mediaappmusic.Helpers.HttpPostRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class APIServiceMusicApp {
    private static String hostAPI = "https://music-cvtpro.000webhostapp.com/Server/";
    private static String LOG_TAG = "Base API Service";

    public static APIServiceMusicApp getInstance() {
        return new APIServiceMusicApp();
    }

    private APIServiceMusicApp() {}

    private String getRequest(String path){
        try {
            HttpGetRequest httpGetRequest = new HttpGetRequest();
            httpGetRequest.execute(hostAPI + path);
            Log.i("BaseAPI", httpGetRequest.get());
            return httpGetRequest.get();
        } catch (Exception e) {
            Log.e(LOG_TAG, "getRequest: " + e.getMessage());
            return String.format("{\"err\":\"{0}\",\"mess:\":\"{1}\"}","404",e.getMessage());
        }
    }

    private String postRequest(String path, String params) {
        try {
            HttpPostRequest httpPostRequest = new HttpPostRequest();
            httpPostRequest.execute(hostAPI + path, params);
            Log.i("BaseAPI", httpPostRequest.get());
            return httpPostRequest.get();
        } catch (Exception e) {
            Log.e(LOG_TAG, "getRequest: " + e.getMessage());
            return String.format("{\"err\":\"{0}\",\"mess:\":\"{1}\"}","404",e.getMessage());
        }
    }

    public String getAccount() {
        return getRequest("getAccountOnAndroid.php");
    }

    public String login(String username, String password) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return postRequest("login.php", jsonObject.toString());
    }
}

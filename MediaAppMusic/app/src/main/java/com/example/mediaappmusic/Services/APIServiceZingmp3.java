package com.example.mediaappmusic.Services;

import android.util.Log;

import com.example.mediaappmusic.Helpers.HttpGetRequest;

public class APIServiceZingmp3 {
//    private static String hostAPI = "https://zingmp3api-dvn.onrender.com/";
    private static String hostAPI = "https://cringe-mp3-api.vercel.app/";
    private static String LOG_TAG = "Base API Service";

    public static APIServiceZingmp3 getInstance() {
        return new APIServiceZingmp3();
    }

    private APIServiceZingmp3() {}

    private String getRequest(String path){
        try {
            HttpGetRequest httpGetRequest = new HttpGetRequest();
            httpGetRequest.execute(hostAPI + path);
            Log.i("BaseAPI", httpGetRequest.get());
            return httpGetRequest.get();
        } catch (Exception e) {
            Log.e(LOG_TAG, "getRequest: " + e.getMessage());
            return String.format("{\"error\":\"{0}\",\"mess:\":\"{1}\"}","404",e.getMessage());
        }
    }
    public String getPlayListTop100(){ return getRequest("top100"); }
    public String getDetailsAlbumOrPlayList(String id) { return getRequest("getDetailPlaylist/" + id); }
    public String getFullInfo(String id) { return getRequest("getFullInfo/" + id); }
    public String getStreaming(String id) { return getRequest("getStreaming/" + id); }

    public  String get() {return getRequest("getAccountOnAndroid.php");}
}

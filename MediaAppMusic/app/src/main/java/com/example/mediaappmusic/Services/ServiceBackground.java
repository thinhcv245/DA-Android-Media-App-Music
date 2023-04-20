package com.example.mediaappmusic.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.DTO.ObjectDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ServiceBackground extends IntentService {

    static ArrayList<ObjectDTO> objectDTOS = new ArrayList<>();
    static ArrayList<AlbumDTO> listAlbum = new ArrayList<>();
    Gson gson = new Gson();

    public static ArrayList<ObjectDTO> getTop100() {
        return objectDTOS;
    }
    public static AlbumDTO getAlbum(String idAlbum) {
        for(AlbumDTO al : listAlbum) {
            if(al.getEncodeId().equals(idAlbum)) {
                return al;
            }
        }
        return null;
    }

    public ServiceBackground() {
        super("ServiceBackground");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Call API", "Vô");
        CallPlayListTop100();
        Log.d("Call API", "Xong");
        for(ObjectDTO objectDTO : objectDTOS) {
            Log.d("ObjectectDTO name:", objectDTO.getTitle());
            ArrayList<AlbumDTO> albumDTOSOfObjectDTO = objectDTO.getItems();
            for(AlbumDTO albumDTO : albumDTOSOfObjectDTO) {
                String api = APIServiceZingmp3.getInstance().getDetailsAlbumOrPlayList(albumDTO.getEncodeId());
                if(!api.contains("\"error\"")) {
                    AlbumDTO al = gson.fromJson(api, AlbumDTO.class);
                    listAlbum.add(al);
                    Log.d("album :", al.getEncodeId());
                }
            }
        }
    }

    private void CallPlayListTop100() {
        String api = APIServiceZingmp3.getInstance().getPlayListTop100();
        try {
            if (!api.contains("err")) {
                objectDTOS = gson.fromJson(api, new TypeToken<ArrayList<ObjectDTO>>() {
                }.getType());
            }
        } catch (Exception e) {
            Log.e("Error call API: ", e.getMessage());
        }
    }
}

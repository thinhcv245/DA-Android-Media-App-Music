package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mediaappmusic.APIs.IAPIData;
import com.example.mediaappmusic.DTO.AlbumDTO;
import com.example.mediaappmusic.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumOrPlayListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_or_play_list);

        callGetDetails();
    }

    private void callGetDetails() {
        IAPIData.iApiService.getDetailsAlbumOrPlayList("ZWZB969E").enqueue(new Callback<AlbumDTO>() {
            @Override
            public void onResponse(Call<AlbumDTO> call, Response<AlbumDTO> response) {
                if(response.isSuccessful()) {
                    AlbumDTO albumDTO = response.body();
                    Log.d("Check: ", String.valueOf(albumDTO.getSong() == null));
                    Log.d("Test: ", albumDTO.getSong().getItems().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<AlbumDTO> call, Throwable t) {
                Toast.makeText(AlbumOrPlayListActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
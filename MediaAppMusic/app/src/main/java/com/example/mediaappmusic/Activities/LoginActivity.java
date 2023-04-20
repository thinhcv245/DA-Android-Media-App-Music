package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mediaappmusic.DTO.AccountDTO;
import com.example.mediaappmusic.Helpers.HttpPostRequest;
import com.example.mediaappmusic.R;
import com.example.mediaappmusic.Services.APIServiceMusicApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView btnLogin, linkRegister, forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username            = findViewById(R.id.login_editText_username);
        password            = findViewById(R.id.login_editText_password);
        btnLogin            = findViewById(R.id.login_textView_login);
        linkRegister        = findViewById(R.id.login_textView_register);
        forgotPassword      = findViewById(R.id.login_textView_forgotpassword);

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        Gson gson = new Gson();
//        String api = APIServiceMusicApp.getInstance().getAccount();
//        ArrayList<AccountDTO> accountDTOS = gson.fromJson(api, new TypeToken<ArrayList<AccountDTO>>(){}.getType());
//        for(AccountDTO accountDTO : accountDTOS) {
//            Log.d("Account:", accountDTO.getUsername() + ", " + accountDTO.getPassword());
//        }

    }
}
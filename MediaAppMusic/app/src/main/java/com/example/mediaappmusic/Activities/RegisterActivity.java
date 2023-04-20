package com.example.mediaappmusic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaappmusic.Helpers.Utilities;
import com.example.mediaappmusic.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, comfirmPassword;
    TextView btnRegister, linkLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username            = findViewById(R.id.register_editText_username);
        password            = findViewById(R.id.register_editText_password);
        comfirmPassword     = findViewById(R.id.register_editText_confirmPassword);
        btnRegister         = findViewById(R.id.register_textView_register);
        linkLogin           = findViewById(R.id.register_textView_login);

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Utilities.checkMail(username.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().isEmpty() || password.getText().toString().length() < 7) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu ít nhất phải 7 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.getText().toString().equals(comfirmPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}
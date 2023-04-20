package com.example.mediaappmusic.DTO;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    String Username;
    String Password;

    public AccountDTO(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

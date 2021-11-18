package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String hashPassword;
    String salt;
    String token;



    public User(String username, String hashPassword, String salt, String token) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.salt = salt;
        this.token = token;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }


}

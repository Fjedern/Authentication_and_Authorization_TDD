package org.example;

public class User {
    String username;
    String hashPassword;
    String salt;
    int token;

    public User(String username, String hashPassword, String salt, int token) {
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

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
}

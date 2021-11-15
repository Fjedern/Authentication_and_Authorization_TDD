package org.example;

public class LoginHandler {

    public Login startLogin(String username, String password){
        return new Login(username, password);
    }
}

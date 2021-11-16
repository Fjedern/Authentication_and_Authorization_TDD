package org.example;

import java.util.HashMap;
import java.util.Map;

public class LoginHandler {

    static Map<String, Login> savedLogins = new HashMap<>();

    public static Boolean startLogin(String username, String password){
        Login login = savedLogins.get(username);
        if(username == null){
            System.out.println("No username");  //Change for exception
            return false;
        }

        if(password.equals(login.getPassword())){
            return true;
        } else {
            return false;
        }

    }

    public void addAllUsers() {
        savedLogins.put("anna", new Login("anna", "losen"));
        savedLogins.put("berit", new Login("berit", "123456"));
        savedLogins.put("kalle", new Login("kalle", "password"));
    }
}

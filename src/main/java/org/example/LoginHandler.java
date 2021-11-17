package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.example.HashHandler.*;

public class LoginHandler {

    static Map<String, User> hashedUserList = new HashMap<>();

    public static int startLogin(String username, String password){
        User currentUser = hashedUserList.get(username);
        if(username == null){
            System.out.println("No username");  //Change for exception
            return 0;
        }
        boolean unhash = verifyPassword(password,
                currentUser.getHashPassword(),
                currentUser.getSalt());
        if(unhash){
            return currentUser.getToken();
        } else {
            System.out.println("Wrong");
            return 0;
        }
    }

    public void addUser(String username, String password) {
        String tmpSalt = generateSalt(5).get();
        String tmpHash = hashPassword(password, tmpSalt).get();
        Random rand = new Random();
        int tokenGen = rand.nextInt(50 + hashedUserList.size());
        hashedUserList.put(username, new User(username,
                tmpHash,
                tmpSalt,
                tokenGen));
    }
}

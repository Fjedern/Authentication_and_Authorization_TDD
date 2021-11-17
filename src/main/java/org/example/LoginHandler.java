package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.example.HashHandler.*;

public class LoginHandler {

    static Map<String, User> userList = new HashMap<>();

    public String startLogin(String username, String password) throws WrongLoginException {
        User currentUser = userList.get(username);
        if(username == null){
            System.out.println("No username");  //Change for exception
            throw new WrongLoginException("Wrong login");
        }
        boolean validate = verifyPassword(password,
                currentUser.getHashPassword(),
                currentUser.getSalt());
        if(validate){
            return currentUser.getToken();
        } else {
            throw new WrongLoginException("Wrong login");
        }
    }

    public void addUser(String username, String password) {
        String tmpSalt = generateSalt(5).get();
        String tmpHash = hashPassword(password, tmpSalt).get();
        Random rand = new Random();
        int tokenGen = rand.nextInt(50 + userList.size());
        userList.put(username, new User(username,
                tmpHash,
                tmpSalt,
                Integer.toString(tokenGen)));
    }
}

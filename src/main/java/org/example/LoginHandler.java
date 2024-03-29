package org.example;

import java.util.*;


public class LoginHandler {

    Map<String, User> userList = new HashMap<>();
    Map <String, UserPermission> userPermissions = new HashMap<>();

    private final HashHandler hashHandler;

    public LoginHandler() {
        hashHandler = new HashHandler();
    }

    public String startLogin(String username, String password) throws WrongLoginException {
        User currentUser = userList.get(username);
        if(username == null){
            throw new WrongLoginException("Wrong username");
        }
        boolean validate = hashHandler.verifyPassword(password,
                currentUser.getHashPassword(),
                currentUser.getSalt());
        if(validate){
            return currentUser.getToken();
        } else {
            throw new WrongLoginException("Wrong password");
        }
    }

    public void addUser(String username, String password) {
        String tmpSalt = hashHandler.generateSalt(5).get();
        String tmpHash = hashHandler.hashPassword(password, tmpSalt).get();
        userList.put(username, new User(username,
                tmpHash,
                tmpSalt,
                generateUniqueToken()));
    }

    /*private String generateUniqueToken(){
        Random rand = new Random();
        int token = rand.nextInt(50 + userList.size());

        if(!validateToken(Integer.toString(token)))
            token = rand.nextInt(50 + userList.size());

        return Integer.toString(token);
    }*/

    private String generateUniqueToken(){
        return UUID.randomUUID().toString();
    }

    public boolean validateToken(String token) {
        for (User value : userList.values()) {
            if(value.getToken().equals(token))
                return true;
        }
        return false;
    }

    public void addPermissionsToResourceForUser(String username, String resource, List<Permissions> permissions) {
        User user = userList.get(username);
        userPermissions.put(user.getToken(), new UserPermission(user.getToken(), resource, permissions));
    }

    public List<Permissions> getPermission(String token, String resource) {
        UserPermission user = userPermissions.get(token);
        return user.getPermissions(resource);
    }

    public class UserPermission {
        String token;
        Map<String, List<Permissions>> resourcesPermissions = new HashMap<>();

        public UserPermission(String token, String resource, List<Permissions> permissions) {
            this.token = token;
            resourcesPermissions.put(resource, permissions);
        }

        public List<Permissions> getPermissions(String resource) {
            return resourcesPermissions.get(resource);
        }
    }
}

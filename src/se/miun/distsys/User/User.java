package se.miun.distsys.User;

import java.util.HashMap;

public class User {
    private String username;
    HashMap<User, Integer> users = new HashMap<User, Integer>();

    public User(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username; 
    }
}

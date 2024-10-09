package se.miun.distsys.manager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;


public class UserManager {
    private static List<UserManager> managerList = new ArrayList<>();
    private Map<String, User> userList; // Cannot infer type arguments for hashmap? fix java.until.Map

    public UserManager(){
        userList = new HashMap<>();
    }

    public void addUser(User user){
        userList.put(user.getId(), user);
    }
    public void removeUser(User user){
        userList.remove(user.getId());
    }
    public User getUser(String id){
        return userList.get(id);
    }
    public List<User> getUserList(){
        return new ArrayList<>(userList.values());
    }
    public boolean userExists(String username) {
        return userList.containsKey(username);
    }
    public void updateList(List <User> users){
        userList.clear();
        for(User user : users){
            userList.put(user.getId(), user);
        }
    }
  
}
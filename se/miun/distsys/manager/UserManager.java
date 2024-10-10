package se.miun.distsys.manager;

import javax.swing.*;

import se.miun.distsys.GroupCommunication;
import se.miun.distsys.messages.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;

public class UserManager {
    //private static List<UserManager> managerList = new ArrayList<>();
    private Map<String, User> userList; // Cannot infer type arguments for hashmap? fix java.until.Map
    private GroupCommunication gc;

    public UserManager(GroupCommunication gc){
        this.gc = gc;
        this.userList = new HashMap<>();
    }

    public void addUser(User user){
        userList.put(user.getId(), user);
        System.out.println("User added: " + user.getUsername());  // Debug: Log the added user

        gc.sendJoinMessage(new JoinMessage(user));
    }
    public void removeUser(User user){
        
        gc.sendLeaveMessage(new LeaveMessage(user));
        userList.remove(user.getId());
    }
    public User getUser(String id){
        return userList.get(id);
    }
    public List<User> getUserList(){
        return new ArrayList<>(userList.values());
    }
    public boolean userExists(String username) {
        for (User user : userList.values()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public void handleJoinMessage(JoinMessage joinMessage){ // bör jag skicka här?
        User user = joinMessage.getUser();
        if(!userExists(user.getId())){
            userList.put(user.getId(), user);
        }
    }
    public void handleLeaveMessage(LeaveMessage leaveMessage) { // bör jag skicka här?
        User leavingUser = leaveMessage.getUser();
        if (userList.containsKey(leavingUser.getId())) {
            userList.remove(leavingUser.getId());
         } // 
    }
    public void updateList(List <User> users){
        userList.clear();
        for(User user : users){
            userList.put(user.getId(), user);
        }
    }
  
}
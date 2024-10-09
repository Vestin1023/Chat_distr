package se.miun.distsys.manager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private static List<User> userList = new ArrayList<>(); // behövs denna?
    private String id;
    private String username;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.username = createUsername();
        userList.add(this);
    }

    private String createUsername() {
        return JOptionPane.showInputDialog(null, "Enter your username:", "Username Creation", JOptionPane.PLAIN_MESSAGE);
    }

    public static void removeUser(User user) {
        userList.remove(user);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public static List<User> getUserList() {
        return userList;
    }
}
// test commit

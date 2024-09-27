package se.miun.distsys.manager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserManager {
    private static List<UserManager> managerList = new ArrayList<>();
    private String id;
    private String username;

    public UserManager() {
        this.id = UUID.randomUUID().toString();
        this.username = createUsername();
        managerList.add(this);
    }

    private String createUsername() {
        return JOptionPane.showInputDialog(null, "Enter your username:", "Username Creation", JOptionPane.PLAIN_MESSAGE);
    }

    public static void removeManager(UserManager manager) {
        managerList.remove(manager);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public static List<UserManager> getManagerList() {
        return managerList;
    }

    public static void main(String[] args) {
        UserManager manager1 = new UserManager();
        System.out.println("Manager created: " + manager1.getUsername() + " with ID: " + manager1.getId());

        UserManager manager2 = new UserManager();
        System.out.println("Manager created: " + manager2.getUsername() + " with ID: " + manager2.getId());

        System.out.println("Current managers: ");
        for (UserManager manager : managerList) {
            System.out.println(manager.getUsername() + " with ID: " + manager.getId());
        }

        removeManager(manager1);
        System.out.println("Manager " + manager1.getUsername() + " removed.");

        System.out.println("Current managers: ");
        for (UserManager manager : managerList) {
            System.out.println(manager.getUsername() + " with ID: " + manager.getId());
        }
    }
}
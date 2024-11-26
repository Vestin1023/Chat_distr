package se.miun.distsys.messages;

import java.io.Serializable;

public class LeaveMessage extends Message implements Serializable {
    public String username;
    
    public LeaveMessage(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
}

package se.miun.distsys.messages;

import java.io.Serializable;

public class JoinMessage extends Message implements Serializable {
    public String username;
    
    public JoinMessage(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
}
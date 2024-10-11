package se.miun.distsys.messages;

public class JoinMessage extends Message {
    public String username;
    
    public JoinMessage(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
}
package se.miun.distsys.messages;

import se.miun.distsys.manager.User;

public class LeaveMessage extends Message {
    private User user;

    public LeaveMessage(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
}

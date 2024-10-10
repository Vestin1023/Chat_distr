package se.miun.distsys.messages;

import se.miun.distsys.manager.User;

public class JoinMessage extends Message {
    private User user;

    public JoinMessage(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

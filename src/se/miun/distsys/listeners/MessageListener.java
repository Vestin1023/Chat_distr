package se.miun.distsys.listeners;

import se.miun.distsys.messages.Message;

public interface MessageListener {

    void onMessageReceived(Message message);
    
}

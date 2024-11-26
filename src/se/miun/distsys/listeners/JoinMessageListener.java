package se.miun.distsys.listeners;

import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.Message;


public class JoinMessageListener implements MessageListener {

    @Override
    public void onMessageReceived(Message message) {
        if (message instanceof JoinMessage) {
            handleJoinMessage((JoinMessage) message);
        }
    }
    private void handleJoinMessage(JoinMessage joinMessage) {
        System.out.println("Received join message: " + joinMessage.getUsername());
        // Additional handling logic for join messages
    }
    
}

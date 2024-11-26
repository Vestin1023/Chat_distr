package se.miun.distsys.listeners;

import se.miun.distsys.messages.Message;
import se.miun.distsys.messages.LeaveMessage;



public class LeaveMessageListener implements MessageListener {

    @Override
    public void onMessageReceived(Message message) {
        if (message instanceof LeaveMessage) {
            LeaveMessage leaveMessage = (LeaveMessage) message;
            System.out.println("User left: " + leaveMessage.getUsername());
            // Add logic to remove the user from the system
        }
    }
    
}

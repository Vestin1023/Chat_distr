package se.miun.distsys.listeners;

import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;

public interface JoinLeaveMessageListener {
    void onIncomingJoinMessage(JoinMessage joinMessage);
    void onIncomingLeaveMessage(LeaveMessage leaveMessage);
    
}

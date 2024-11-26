package se.miun.distsys.listeners;

import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.Message;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;

public interface ChatMessageListener extends MessageListener {

    public void onIncomingLeaveMessage(LeaveMessage leaveMessage);
    public void onIncomingJoinMessage(JoinMessage joinMessage);
    public void onIncomingChatMessage(ChatMessage chatMessage);
}

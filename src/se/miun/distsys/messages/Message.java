package se.miun.distsys.messages;

import java.io.Serializable;

public class Message implements Serializable{
    public class JoinMessage extends Message {
        public String username;
    
        public JoinMessage(String username) {
            this.username = username;
        }
    }
    
    public class LeaveMessage extends Message {
        public String username;
    
        public LeaveMessage(String username) {
            this.username = username;
        }
    }
    
    public class ChatMessage extends Message {
        public String chat;
    
        public ChatMessage(String chat) {
            this.chat = chat;
        }
    }
}





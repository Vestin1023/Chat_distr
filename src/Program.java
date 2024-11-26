import java.io.BufferedReader;
import java.io.InputStreamReader;

import se.miun.distsys.GroupCommunication;
import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;

//Skeleton code for Distributed systems

public class Program implements ChatMessageListener{

	boolean runProgram = true;
	
	GroupCommunication gc = null;

    @Override
    public void onIncomingJoinMessage(JoinMessage joinMessage) {
        // Handle incoming join message
        System.out.println("User joined: " + joinMessage.username);
    }

    @Override
    public void onIncomingLeaveMessage(LeaveMessage leaveMessage) {
        // Handle incoming leave message
        System.out.println("User left: " + leaveMessage.username);
    }
	
	public static void main(String[] args) {
		Program program = new Program();
	}
		
	public Program() {
		gc = new GroupCommunication();
		gc.setChatMessageListener(this);
		System.out.println("Group Communication Started");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		while(runProgram) {			
			try {
				
				System.out.println("Write message to send: ");	
				String chat = br.readLine();			
				gc.sendChatMessage(chat);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		gc.shutdown();
	}

	@Override
	public void onIncomingChatMessage(ChatMessage chatMessage) {		
		System.out.println("Incoming chat message: " + chatMessage.chat);	
	}
}
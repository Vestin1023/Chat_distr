package se.miun.distsys;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.messages.ChatMessage;
import se.miun.distsys.messages.JoinMessage;
import se.miun.distsys.messages.LeaveMessage;
import se.miun.distsys.messages.Message;
import se.miun.distsys.messages.MessageSerializer;


public class GroupCommunication {
	
	private final int datagramSocketPort = 3643; //You need to change this!
	DatagramSocket datagramSocket = null;	
	boolean runGroupCommunication = true;
	MessageSerializer messageSerializer = new MessageSerializer();

	//Listeners
	ChatMessageListener chatMessageListener = null;	
	public void setChatMessageListener(ChatMessageListener listener) {
		this.chatMessageListener = listener;		
	} 

	public void sendJoinMessage(String username) {
		JoinMessage joinMessage = new JoinMessage(username);
		sendMessage(joinMessage);

	}
	public void sendLeaveMessage(String username) {
		LeaveMessage leaveMessage = new LeaveMessage(username);
		sendMessage(leaveMessage);
	}

	
	public void handleIncomingMessage(Message message) {
        if (message instanceof JoinMessage) {
            JoinMessage joinMessage = (JoinMessage) message;
            if (chatMessageListener != null) {
                chatMessageListener.onIncomingJoinMessage(joinMessage);
            }
        } else if (message instanceof LeaveMessage) {
            LeaveMessage leaveMessage = (LeaveMessage) message;
            if (chatMessageListener != null) {
                chatMessageListener.onIncomingLeaveMessage(leaveMessage);
            }
        }
    }

	public void sendChatMessage(String text) {
        ChatMessage chatMessage = new ChatMessage(text);
        sendMessage(chatMessage);
    }
	//UDP Logic
	private void sendMessage(Message message) {
		try {
			byte[] sendData = messageSerializer.serializeMessage(message);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255" /*Broadcast address*/), datagramSocketPort);
			datagramSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}






	public GroupCommunication() {
		try {
			datagramSocket = new MulticastSocket(datagramSocketPort);
						
			ReceiveThread rt = new ReceiveThread();
			rt.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		runGroupCommunication = false;
	}
	

	class ReceiveThread extends Thread{
		Random r = new Random();
		int chanceToDropPackets = 0;

		@Override
		public void run() {
			byte[] buffer = new byte[65536];		
			DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
			
			while(runGroupCommunication) {
				try {
					datagramSocket.receive(datagramPacket);										
					byte[] packetData = datagramPacket.getData();					
					Message receivedMessage = messageSerializer.deserializeMessage(packetData);

					if(receivedMessage instanceof ChatMessage && r.nextInt(100) < chanceToDropPackets){
						System.out.println("Dropped packet");
					} else {
						handleMessage(receivedMessage);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
				
		private void handleMessage (Message message) {
			
			if(message instanceof ChatMessage) {
				ChatMessage chatMessage = (ChatMessage) message;
				if(chatMessageListener != null){
					chatMessageListener.onIncomingChatMessage(chatMessage);
				}
			} else {				
				System.out.println("Unknown message type");
			}
		}
	}	
	

	
}

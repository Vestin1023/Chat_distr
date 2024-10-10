import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import se.miun.distsys.GroupCommunication;
import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.messages.ChatMessage;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;

//Skeleton code for Distributed systems

public class WindowProgram implements ChatMessageListener, ActionListener {

	JFrame frame;
	JTextPane txtpnChat = new JTextPane();
	JTextPane txtpnMessage = new JTextPane();
	
	GroupCommunication gc = null;
	DefaultListModel<String> userListModel = new DefaultListModel<>();
    JList<String> userList = new JList<>(userListModel);
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowProgram window = new WindowProgram();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WindowProgram() {
		initializeFrame();

		gc = new GroupCommunication();
		gc.setChatMessageListener(this);
		System.out.println("Group Communication Started");
	}

	private void initializeFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//left side
		JScrollPane chatScrollPane = new JScrollPane(txtpnChat);
        txtpnChat.setEditable(false);
        txtpnChat.setText("--== Group Chat ==--");
        frame.getContentPane().add(chatScrollPane, BorderLayout.CENTER);

		//right side
		JScrollPane userScrollPane = new JScrollPane(userList);
        userScrollPane.setPreferredSize(new java.awt.Dimension(150, 0));
        frame.getContentPane().add(userScrollPane, BorderLayout.EAST);

		//down side
		
		JPanel inputPanel = new JPanel(new BorderLayout());
        txtpnMessage.setText("Message");
        inputPanel.add(txtpnMessage, BorderLayout.CENTER);

        JButton btnSendChatMessage = new JButton("Send");
        btnSendChatMessage.addActionListener(this);
        btnSendChatMessage.setActionCommand("send");
        inputPanel.add(btnSendChatMessage, BorderLayout.EAST);
        
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
	        public void windowClosing(WindowEvent winEvt) {
	            gc.shutdown();
	        }
	    });
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equalsIgnoreCase("send")) {
			gc.sendChatMessage(txtpnMessage.getText());
		}		
	}
	
	@Override
	public void onIncomingChatMessage(ChatMessage chatMessage) {	
		txtpnChat.setText(chatMessage.chat + "\n" + txtpnChat.getText());
		userListModel.addElement("New User");				
	}
}

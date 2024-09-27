import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import se.miun.distsys.GroupCommunication;
import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.messages.ChatMessage;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

//Skeleton code for Distributed systems

public class WindowProgram implements ChatMessageListener, ActionListener {

    JFrame frame;
    JTextPane txtpnChat = new JTextPane();
    JTextPane txtpnMessage = new JTextPane();
    DefaultListModel<String> userListModel = new DefaultListModel<>(); // To hold the list of users
    JList<String> userList = new JList<>(userListModel); // Display the user list

    GroupCommunication gc = null;

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

    public void initializeFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400); // Adjusted frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title Area
        JLabel titleLabel = new JLabel("Chat Application", JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createTitledBorder(""));
        frame.add(titleLabel, BorderLayout.NORTH); 

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setPreferredSize(new Dimension(300, 400)); 
        
        // Chat area (70% height)
        JScrollPane scrollPane = new JScrollPane(txtpnChat);
        txtpnChat.setEditable(false);
        txtpnChat.setBorder(BorderFactory.createTitledBorder("--== Group Chat ==--"));
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Typing and button area (30% height)
        JPanel typingPanel = new JPanel(new BorderLayout());
        txtpnMessage.setText("Message");
        txtpnMessage.setPreferredSize(new Dimension(300, 100)); 
        typingPanel.add(txtpnMessage, BorderLayout.CENTER);

        // Send button to the right of the typing area
        JButton btnSendChatMessage = new JButton("Send Chat Message");
        btnSendChatMessage.addActionListener(this);
        btnSendChatMessage.setActionCommand("send");
        typingPanel.add(btnSendChatMessage, BorderLayout.EAST);

        chatPanel.add(typingPanel, BorderLayout.SOUTH);
        
        frame.add(chatPanel, BorderLayout.WEST);

        JPanel userListPanel = new JPanel(new BorderLayout());
        userListPanel.setBorder(BorderFactory.createTitledBorder("Users Connected"));
        JScrollPane userScrollPane = new JScrollPane(userList);
        userListPanel.add(userScrollPane, BorderLayout.CENTER);

        // Add user list panel to the right (50% width)
        frame.add(userListPanel, BorderLayout.CENTER);

        // Window closing listener to handle shutdown
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
    }
}
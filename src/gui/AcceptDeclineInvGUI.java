package gui;

import Calender.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Accepts the messages that this user recieves
 */
public class AcceptDeclineInvGUI extends JFrame{
    private JPanel panel;
    private JScrollPane scrollPane;
    private JLabel display;
    private JTextField textField1;
    private JButton backButton;
    private JButton acceptButton;
    private JButton declineButton;
    private JScrollPane scrollPaneCalender;
    private JTextField textField2;
    private JLabel calenderDisplay;
    private Pattern pattern;
    private User user;

    public AcceptDeclineInvGUI(User user){
        super("Accepting Invitations");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.user = user;
        this.pattern = Pattern.compile("(\\d*\\s?)*");
        this.setMinimumSize(new Dimension(650, 450));
        scrollPane.createVerticalScrollBar();
        setSelectionText();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


    }

    /**
     * Helper method to set up the gui
     * Sets the display for the scroll panel and accordingly changes the functionality of buttons
     */
    private void setSelectionText(){
        List<Message> messageList = user.getMessageList();
        //If there are no invitations then change the display and done button
        if(messageList.isEmpty()){
            display.setText("No Invitations!");
            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            declineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        } else {
            setDisplay(messageList);
            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!pattern.matcher(textField1.getText()).matches() && !pattern.matcher(textField2.getText()).matches()) {
                        ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in AcceptDeclineInvitationGUI)");
                    } else {
                        acceptInvitations(messageList);
                        dispose();
                    }
                }
            });
            declineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!pattern.matcher(textField1.getText()).matches()) {
                        ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in AcceptDeclineInvitationGUI)");
                    } else {
                        declineInvitations(messageList);
                        dispose();
                    }
                }
            });
        }
    }

    /**
     * Set the display for the scroll pane. Display all the messages
     * @param messageList the list of messages to display
     */
    private void setDisplay(List<Message> messageList){
        String temp = "";
        for (int i = 0; i < messageList.size(); i++) {
            temp += i + ") " + messageList.get(i) + "<br/>";
        }
        display.setText("<html>" + temp+"</html>");
        temp ="";
        for (int i = 0; i < user.getAllCalender().size(); i++) {
            temp += i + ") " + user.getAllCalender().get(i) + "<br/>";
        }
        calenderDisplay.setText("<html>" + temp+"</html>");
    }

    /**
     * Accepts the invitations from the user's input
     * @param messages
     */
    public void acceptInvitations(List<Message> messages){
        String[] index = textField1.getText().split(" ");

        List<Message> messagesCopy = new ArrayList<Message>();
        List<Calender> calenderList = new ArrayList<Calender>();

        messagesCopy.addAll(messages);

        // Make sure that the accepted string isnt empty and then accept the invitation
        if(!textField2.getText().equals("")){
            //Get all the calenders specified by the user to add the event invitation into
            String[] calIndex = textField2.getText().split(" ");
            for(String ind : calIndex){
                calenderList.add(user.getAllCalender().get(Integer.valueOf(ind)));
            }
        }

        //Get the message and accept it then delete it from the user's list of messages
        for(String ind: index){
            Message tempMsg = messagesCopy.get(Integer.valueOf(ind));
            tempMsg.accept(calenderList);
        }
    }

    /**
     * Declines the invitations that this user specifies
     * @param messages
     */
    public void declineInvitations(List<Message> messages){
        String[] index = textField1.getText().split(" ");
        List<Message> messagesCopy = new ArrayList<Message>();
        messagesCopy.addAll(messages);

        for(String ind: index){
            Message tempMsg = messagesCopy.get(Integer.valueOf(ind));
        }
    }

}

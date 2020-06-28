package gui;

import Calender.*;
import Calender.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateEventInv extends JFrame{
    private JButton backButton;
    private JButton doneButton;
    private JScrollPane scrollPane;
    private JLabel display;
    private JTextField textField1;
    private JPanel panel;
    private JScrollPane scrollPaneUser;
    private JLabel displayUser;
    private JTextField textField2;
    private Pattern pattern;
    private List<User> userList;
    private User user;
    private List<Event> events;

    public CreateEventInv(User user, List<User> userList){
        super("Creating Event Invitations");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.pattern = Pattern.compile("(\\d*\\s?)*");
        this.userList = userList;
        this.user = user;
        this.setVisible(true);

        events = new ArrayList<Event>();
        for(Calender c: user.getAllCalender()){
            events.addAll(c.getEvents());
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setUserPane();
        this.setMinimumSize(new Dimension(650, 450));

    }

    private void setEventPane(){
        String temp = "";
        for (int i = 0; i < events.size(); i++) {
            temp += i + ") " + events.get(i) + "<br/>";
        }
        display.setText("<html>" + temp+"</html>");
    }

    private void setUserPane(){
        setEventPane();
        if(userList.isEmpty()){
            displayUser.setText("No Users to invite");
            doneButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        } else {
            setDisplayPane();
            doneButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!pattern.matcher(textField1.getText()).matches() && !pattern.matcher(textField2.getText()).matches()) {
                        ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in CreateGroupCalenderGUI)");
                    } else {
                        sendEventMsg();
                        dispose();
                    }
                }
            });
        }
    }


    /**
     * Helper that creates and send the message to the users
     */
    private void sendEventMsg(){
        String[] eventsIndex = textField1.getText().split(" ");
        String[] userIndex = textField2.getText().split(" ");

        List<Event> msgEvents = new ArrayList<Event>();

        for (String inde : eventsIndex) {
            msgEvents.add(events.get(Integer.valueOf(inde)));
        }

        for(String ind: userIndex){
            User usr = userList.get(Integer.valueOf(ind));
            EventMessage eventMessage = new EventMessage(user, usr, msgEvents);
            usr.addMsg(eventMessage);
        }
    }

    /**
     * Helper that display the correct info onto the displayUser pane
     */
    private void setDisplayPane(){
        String temp = "";
        for (int i = 0; i < userList.size(); i++) {
            temp += i + ") " + userList.get(i) + "<br/>";
        }
        displayUser.setText("<html>" + temp+"</html>");
    }
}

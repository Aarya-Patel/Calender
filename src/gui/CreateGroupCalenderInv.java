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
 * Recieves user input and sends the messages to the corresponding users
 */
public class CreateGroupCalenderInv extends JFrame {
    private JPanel panel;
    private JLabel display;
    private JTextField textField1;
    private JButton doneButton;
    private JScrollPane scrollPane;
    private JButton backButton;
    private Pattern pattern;
    private ChooseGUI<Calender> calenderChooseGUI;
    private User user;
    private List<User> userList;

    public CreateGroupCalenderInv(User user, List<User> userList) {
        super("Inviting Group Calender");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.pattern = Pattern.compile("(\\d*\\s?)*");
        this.user = user;
        this.userList = userList;

        this.setMinimumSize(new Dimension(650, 450));
        scrollPane.createVerticalScrollBar();
        setSelectionText();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        calenderChooseGUI = new ChooseGUI<Calender>(this, user.getAllCalender());
    }

    /**
     * Send the message to the users in the list
     */
    private void sendInvitation() {
        String[] index = textField1.getText().split(" ");
        List<User> selectedUser = new ArrayList<User>();
        List<Calender> selectedCalender = calenderChooseGUI.getObjectChoices();


        for (String ind : index) {
            selectedUser.add(userList.get(Integer.valueOf(ind)));
        }

        for (User usr : selectedUser) {
            CalenderMessage calenderMessage = new CalenderMessage(user, usr, selectedCalender);
            usr.addMsg(calenderMessage);
        }
    }

    /**
     * Helper method to set up the gui
     * Sets the display for the scroll panel and accordingly changes the functionality of buttons
     */
    private void setSelectionText() {
        if (userList.isEmpty()) {
            display.setText("No other users to invite!");
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
                    if (!pattern.matcher(textField1.getText()).matches()) {
                        ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in CreateGroupCalenderGUI)");
                    } else {
                        sendInvitation();
                        dispose();
                    }
                }
            });
        }
    }

    private void setDisplayPane(){
        String temp = "";
        for (int i = 0; i < userList.size(); i++) {
            temp += i + ") " + userList.get(i) + "<br/>";
        }
        display.setText("<html>" + temp+"</html>");
    }

}

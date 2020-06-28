package gui;

import Calender.*;
import system.menu.UserCalenderMenu;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageBoxGUI extends JFrame{
    private JPanel panel;
    private JButton createCalenderInvitationButton;
    private JButton backButton;
    private JButton createEventInvitationsButton;
    private JButton acceptDeclineInvitationButton;
    private UserCalenderMenu userCalenderMenu;
    private User currentUser;


    public MessageBoxGUI(UserCalenderMenu userCalenderMenu){
        super("MessageBox");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        this.userCalenderMenu = userCalenderMenu;
        this.currentUser = userCalenderMenu.getCurrentUser();



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        createCalenderInvitationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateGroupCalenderInv createGroupCalenderInv = new CreateGroupCalenderInv(currentUser, userCalenderMenu.getUserList());
            }
        });

        acceptDeclineInvitationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcceptDeclineInvGUI acceptInvitationGUI = new AcceptDeclineInvGUI(currentUser);
            }
        });

        createEventInvitationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEventInv createEventInv = new CreateEventInv(currentUser, userCalenderMenu.getUserList());
            }
        });

    }

}

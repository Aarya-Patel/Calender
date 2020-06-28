package gui;

import system.menu.UserCalenderMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCalenderMenuGUI extends JFrame{
    private JLabel title;
    private JPanel contentPanel;
    private JButton returnToLoginButton;
    private JButton createCalenderButton;
    private JButton selectCalenderButton;
    private JButton messageBoxButton;
    private UserCalenderMenu userCalenderMenu;

    public UserCalenderMenuGUI(UserCalenderMenu userCalenderMenu){
        super("User Calender Menu!");
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        returnToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                userCalenderMenu.logoutCurrentUser();
            }
        });

        createCalenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCalenderGUI createCalenderGUI = new CreateCalenderGUI(userCalenderMenu);
            }
        });

        selectCalenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalenderSelectionGUI calenderSelectionGUI = new CalenderSelectionGUI(userCalenderMenu);
            }
        });

        messageBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageBoxGUI messageBoxGUI = new MessageBoxGUI(userCalenderMenu);
            }
        });
    }
}

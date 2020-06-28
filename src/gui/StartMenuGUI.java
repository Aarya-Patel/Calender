package gui;

import system.ConnectDB;
import system.menu.UserCalenderMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuGUI extends JFrame{
    private JLabel title;
    private JButton loginButton;
    private JButton signUpButton;
    private JPanel panel;
    private ConnectDB connectDB;
    private UserCalenderMenu userCalenderMenu;

    public StartMenuGUI(ConnectDB connectDB, UserCalenderMenu userCalenderMenu){
        super("StartMenu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
        this.connectDB = connectDB;
        this.userCalenderMenu = userCalenderMenu;
        this.setMinimumSize(new Dimension(450, 200));


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(connectDB, userCalenderMenu);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signup signup = new Signup(connectDB);
            }
        });
    }
}

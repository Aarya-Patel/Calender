package gui;

import Calender.User;
import system.ConnectDB;
import system.menu.UserCalenderMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {

    private JPanel panel1;
    private JButton loginButton;
    private JTextField usernameTxt;
    private JTextField passwordTxt;
    private ConnectDB connectDB;


    public Login(ConnectDB connectDB, UserCalenderMenu userCalenderMenu) {
        super("Login Menu");
        this.setVisible(true);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.connectDB = connectDB;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    String name = usernameTxt.getText();
                    String password = passwordTxt.getText();
                    User user = connectDB.login(name, password);
                    userCalenderMenu.createMenu(user);
                } catch (Exception e) {
                    ErrorGUI errorGUI = new ErrorGUI("Something went wrong when trying to login. Please try again.");
                }
                dispose();
            }

        });


    }
}

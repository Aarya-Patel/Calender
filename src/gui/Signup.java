package gui;


import system.ConnectDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame {
    private JTextField userNameTxt;
    private JPanel panel1;
    private JTextField userIdTxt;
    private JTextField passwordTxt;
    private JButton signup;
    private JLabel UserName;
    private JLabel UserId;
    private JLabel Password;
    private ConnectDB connectDB;

    public Signup(ConnectDB connectDB) {
        super("Sign Up");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.connectDB = connectDB;

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameTxt.getText();
                String userId = userIdTxt.getText();
                String password = passwordTxt.getText();
//                System.out.println(userName + " " + userId + " " + password);

                try {
                    if (!userName.isEmpty() && !userId.isEmpty() && !password.isEmpty()) {
                        connectDB.register(userName, userId, password);
                        connectDB.updateAllUsers();
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                    ErrorGUI errorGUI = new ErrorGUI("Please use valid inputs only (non-empty)!");
                }
                dispose();
            }
        });

    }
}

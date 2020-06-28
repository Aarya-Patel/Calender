package gui;

import Calender.Calender;
import system.menu.UserCalenderMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCalenderGUI extends JFrame {
    private JLabel title;
    private JPanel contentPanel;
    private JLabel instructionText;
    private JTextField inputField;
    private JButton doneButton;
    private JButton backButton;
    private UserCalenderMenu userCalenderMenu;

    public CreateCalenderGUI(UserCalenderMenu userCalenderMenu) {
        super("Creating Calender!");
        this.userCalenderMenu = userCalenderMenu;
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputField.getText().equals("")) {
                    Calender calender = userCalenderMenu.createCalender(inputField.getText());
                    userCalenderMenu.addCalender(calender);
                }
                dispose();
            }
        });

    }
}

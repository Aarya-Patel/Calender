package gui;

import system.menu.TimeThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetTimeGUI extends JFrame{
    private JTextField textField;
    private JLabel promptText;
    private JButton backButton;
    private JButton doneButton;
    private JPanel setTimePanel;

    public SetTimeGUI(TimeThread timeThread, DateTimeFormatter formatter) {
        super("Setting Time");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(setTimePanel);
        pack();
        setVisible(true);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String timeStr = textField.getText();
                    LocalDateTime newTime = LocalDateTime.parse(timeStr, formatter);
                    timeThread.setTime(newTime);
                }catch (Exception exception){
                    ErrorGUI errorGUI = new ErrorGUI("Please use correct input in the text fields." +
                            " Please adhere to the date format and choose events with proper integers.");
                }
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

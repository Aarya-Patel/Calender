package gui;

import system.manager.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAllGUI extends JFrame {

    private JScrollPane textScrollPane;
    private JButton doneButton;
    private JLabel title;
    private JLabel display;
    private JPanel contentPane;
    private ViewManagement vm;
    private String displayText;

    public ViewAllGUI(String title, ViewManagement vm, String displayText) {
        super(title);
        this.title.setText(title);

        this.vm = vm;
        this.displayText = displayText;

        //JFrame Operations
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        //Set up the view pane
        this.setMinimumSize(new Dimension(650, 450));
        if(!displayText.equals("<html></html>")) {
            display.setText(displayText);
        } else {
            display.setText("Nothing to view here...");
        }


        //Done button simply closes the current gui
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

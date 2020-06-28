package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationGUI extends JFrame{
    private JPanel panel;
    private JButton okButton;
    private JLabel display;

    public NotificationGUI(Object obj){
        super("Notify User");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        this.setMinimumSize(new Dimension(650, 250));

        //Set the display text
        display.setText(obj.toString());

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}

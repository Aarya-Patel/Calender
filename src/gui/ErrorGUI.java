package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorGUI extends JFrame{
    private JPanel contentPanel;
    private JLabel errorLabel;
    private JButton okButton;
    private String errorMessage;

    public ErrorGUI( String errorMessage){
        super("Error");

        this.errorMessage = errorMessage;
        this.errorLabel.setText(errorMessage);

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

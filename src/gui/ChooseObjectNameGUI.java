package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChooseObjectNameGUI<T> extends JFrame {
    private JPanel contentPanel;
    private JButton backButton;
    private JButton doneButton;
    private JLabel title;
    private JLabel display;
    private JScrollPane scrollPanel;
    private JTextField inputField;
    private List<T> objects;
    private JFrame nextFrame;

    public ChooseObjectNameGUI(JFrame nextFrame, List<T> objects){
        super("Selecting Objects to View!");
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.objects = objects;
        this.nextFrame = nextFrame;
        this.setMinimumSize(new Dimension(650, 450));


        setDisplayText();
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
                nextFrame.update(nextFrame.getGraphics());
                nextFrame.setVisible(true);
                dispose();
            }
        });
    }

    public void setDisplayText(){
        String temp = "";
        for(Object obj : objects){
            temp += obj+"<br/>";
        }
        this.display.setText("<html>"+temp+"</html>");
    }

    public String getInput(){
        return inputField.getText();
    }
}

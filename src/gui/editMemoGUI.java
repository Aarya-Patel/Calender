package gui;

import system.manager.CalenderManagement;
import Calender.Memo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editMemoGUI extends JFrame{
    private CalenderManagement cm;
    private JLabel title;
    private JTextField newName;
    private JLabel newNameField;
    private JPanel contentPanel;
    private JTextField newContent;
    private JLabel newContentField;
    private JButton backbutton;
    private JButton doneButton;
    private ChooseSingleObjectGUI<Memo> chooseObj;

    public editMemoGUI(CalenderManagement cm){
        this.cm = cm;

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.chooseObj = new ChooseSingleObjectGUI<Memo>(this, this.cm.calender.getMemos());

        backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = newName.getText();
                    String content = newContent.getText();
                    Memo memo = chooseObj.getObjectChoice();
                    cm.editMemo(memo, name, content);
                } catch (Exception i){
                    ErrorGUI errorGUI = new ErrorGUI("Please put correct input in the text fields.");
                }
                dispose();
            }
        });
    }
}

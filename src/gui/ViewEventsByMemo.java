package gui;

import Calender.*;
import system.manager.ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewEventsByMemo extends JFrame {
    private JPanel contentPanel;
    private JLabel title;
    private JButton okButton;
    private JScrollPane scrollPanel;
    private JLabel display;
    private ViewManagement vm;
    private List<Memo> memoList;
    private ChooseObjectNameGUI<Memo> chooseObjectNameGUI;


    public ViewEventsByMemo(ViewManagement vm, List<Memo> memoList) {
        super("Viewing Events with Memos");
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.vm = vm;
        this.memoList = memoList;
        this.chooseObjectNameGUI = new ChooseObjectNameGUI<Memo>(this, memoList);
        this.setMinimumSize(new Dimension(650, 450));

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public void setDisplayText() {
        String temp = this.vm.viewMemoEvents(chooseObjectNameGUI.getInput());
        this.display.setText(temp);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        setDisplayText();
    }
}
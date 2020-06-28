package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChooseSingleObjectGUI<T> extends JFrame {
    private JPanel contentPanel;
    private JButton nextButton;
    private JLabel title;
    private JLabel instructionText;
    private JTextField index;
    private JLabel display;
    private JScrollPane itemDisplay;
    private JFrame nextframe;
    private List<T> items;


    public ChooseSingleObjectGUI(JFrame nextframe, List<T> items) {
        this.nextframe = nextframe;
        this.items = items;

        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setMinimumSize(new Dimension(650, 450));
        itemDisplay.createVerticalScrollBar();
        setSelectionText();
    }

    public void setSelectionText() {
        if (items.size() == 0) {
            display.setText("No objects have been created!");
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        } else {
            String temp = "";
            for (int i = 0; i < items.size(); i++) {
                temp += i + ") " + items.get(i) + "<br/>";
            }
            display.setText("<html>" + temp + "</html>");

            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (Integer.parseInt(index.getText()) > items.size() - 1) {
                            dispose();
                            System.out.println("AS");
                            ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in ChooseSingleObjGUI)");
                        } else {
                            nextframe.setVisible(true);
                            dispose();
                        }
                    } catch (NumberFormatException i){
                        dispose();
                        System.out.println("WQE");
                        ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in ChooseSingleObjGUI)");

                    }
                }
            });
        }
    }

    public T getObjectChoice(){
        T obj = null;
        try{
            int target = Integer.parseInt(index.getText());
            obj = this.items.get(target);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            System.out.println("in getOBJChoice");
            ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in ChooseSingleObjectGUI)");
        }
        return obj;
    }
}

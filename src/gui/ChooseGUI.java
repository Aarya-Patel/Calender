package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChooseGUI<T> extends JFrame {
    private JLabel title;
    private JTextField selectionInput;
    private JLabel instructionText;
    private JButton nextButton;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JLabel display;
    private List<T> items;
    private JFrame nextFrame;
    private Pattern pattern;

    /**
     * The constructor for this class
     *
     * @param nextFrame the next JFrame to create after this
     * @param items     the user's calender to print the events
     */
    public ChooseGUI(JFrame nextFrame, List<T> items) {
        super("Choosing Objects!");

        this.items = items;
        this.nextFrame = nextFrame;
        this.pattern = Pattern.compile("(\\d*\\s?)*");

        //JFrame Operations
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setMinimumSize(new Dimension(650, 450));
        scrollPane.createVerticalScrollBar();
        setSelectionText();

    }

    /**
     * Reads the events in the calender and sets the specific settings accordingly
     * If there are no events then the nextbutton just disposes itself
     * Otherwise the nextframe is set visible to access
     */
    private void setSelectionText() {
        if (items.size() == 0) {
            display.setText("No objects have been created!");
            //By defualt make the next button dispose itself
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        } else {
            setDisplay();
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Check if the input matches the pattern
                    if (!pattern.matcher(selectionInput.getText()).matches()) {
                        ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in ChooseGUI)");
                    } else {
                        //If there is a next frame then display that, otherwise dispose this frame
                        try {
                            nextFrame.setVisible(true);
                            dispose();
                        } catch(Exception err){
                            dispose();
                        }
                    }

                }
            });
        }
    }

    /**
     * Set the display text
     */
    private void setDisplay(){
        String temp = "";
        for (int i = 0; i < items.size(); i++) {
            temp += i + ") " + items.get(i) + "<br/>";
        }
        display.setText("<html>" + temp + "</html>");
    }

    /**
     * Based on the input given by the user, select the events and return them in an list
     *
     * @return the list of events
     */
    public List<T> getObjectChoices() {
        List<T> obj = new ArrayList<T>();
        try {
            String inputString = selectionInput.getText();
            //Make sure that the string isnt empty
            if (!inputString.equals("")) {
                String[] input = selectionInput.getText().split(" ");

                for (String index : input) {
                    int tempIndex = Integer.valueOf(index);
                    obj.add(items.get(tempIndex));
                }
            }
        } catch (Exception e) {
            //Error GUI
            ErrorGUI errorGUI = new ErrorGUI("Error with input! Use valid integers. Thank you. (Invalid User Input in ChooseGUI)");
        }
        return obj;
    }
}

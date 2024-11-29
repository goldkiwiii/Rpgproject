package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.GridLayout;

public class CalculatorScreen {

    private JFrame frame;
    private JTextField textBox;

    public static String wholeEq = "";
    int i;
    public static boolean errorOn = false;

    public static String[] type = {"A/C", "+/-", "%", "+", "1", "2", "3", "-", "4", "5", "6", "*", "7", "8", "9", "/", "0", ".", "(-)", "="};

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculatorScreen window = new CalculatorScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public CalculatorScreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().setLayout(null);

        // upper panel for calculation
        JPanel textPanel = new JPanel();
        textPanel.setBounds(0, 0, 350, 65);
        frame.getContentPane().add(textPanel);
        textPanel.setLayout(null);
        JButton[] RequiredButton = new JButton[type.length];

        // create (JTextField) text box
        textBox = new JTextField();
        textBox.setBounds(5, 5, 340, 55);
        textPanel.add(textBox);
        textBox.setColumns(10);


        // operator boxes
        JPanel operatorPanel = new JPanel();
        operatorPanel.setBounds(0, 65, 350, 355);
        frame.getContentPane().add(operatorPanel);
        operatorPanel.setLayout(new GridLayout(0, 4, 0, 0));

        for(int i = 0; i < type.length; i++) {
            RequiredButton[i] = new JButton(type[i]);
            Button myButton = new Button(textBox, RequiredButton[i], type, errorOn);
            myButton.makeButton();
            operatorPanel.add(RequiredButton[i]);
        }
    }
}

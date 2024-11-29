
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class calc extends JFrame implements ActionListener{
    private String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
    };
    private JTextField textField;
    private String operator = "";
    private double firstNum = 0;
    private double secondNum = 0;
    private boolean isOperatorClicked = false;


    public calc() {
        setTitle("Calculator");
        setLayout(new BorderLayout());
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            if (isOperatorClicked){
                textField.setText(command);
                isOperatorClicked = false;
            }
            else {
                textField.setText(textField.getText() + command);
            }
        }
        else if (command.equals("C")) {
            textField.setText("");
            firstNum = secondNum = 0;
            operator = "";
        }
        else if (command.equals("=")){
            secondNum = Double.parseDouble(textField.getText());

            switch (operator) {
                case "+":
                    textField.setText(String.valueOf(firstNum + secondNum));
                    break;
                case "-":
                    textField.setText(String.valueOf(firstNum - secondNum));
                    break;
                case "*":
                    textField.setText(String.valueOf(firstNum * secondNum));
                    break;
                case "/":
                    if (secondNum != 0) {
                        textField.setText(String.valueOf(firstNum / secondNum));
                    } else {
                        textField.setText("Error");
                    }
                    break;
            }
                firstNum = secondNum = 0;
                operator = "";
            }

        else {
            if (!operator.isEmpty()) {
                return;
            }
            operator = command;
            firstNum = Double.parseDouble(textField.getText());
            isOperatorClicked = true;
        }




    }
    public static void main(String[] args) {
        calc login = new calc();
        login.setVisible(true);
    }

}
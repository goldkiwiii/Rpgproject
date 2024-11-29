package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Button extends JButton{

    private JTextField textBox;
    JButton RequiredButton = new JButton();
    private String[] type;
    static boolean errorOn;
    static String current = "";

    public Button(JTextField textBox, JButton RequiredButton, String[] type, boolean errorOn) {
        this.textBox = textBox;
        this.RequiredButton = RequiredButton;
        this.type = type;
        Button.errorOn = errorOn;
    }

    public void makeButton() {
        // create boxes
        RequiredButton.addActionListener(new ActionListener() {

            static boolean operator = true;
            static boolean equal = false;
            static boolean numIn = false;
            static boolean deci = false;
            static boolean negative = false;
            String result = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();

                // run through all the list to match with the given element
                for(int i = 0; i < type.length; i++) {

                    // check for reset button
                    if(str.equals("A/C")) {
                        textBox.setText("");
                        CalculatorScreen.wholeEq = "";
                        errorOn = false;
                        operator = true;
                        equal = false;
                        numIn = false;
                        deci = false;
                        negative = false;
                        break;
                    }

                    if(errorOn) break;

                        // check for "=" sign
                    else if(str.equals("=")) {
                        // CALL FUNCTION FROM THE STACK CLASS
                        if(equal) break;
                        if(!numIn) break;
                        Calculation myCal = new Calculation();
                        result = myCal.equalSign(CalculatorScreen.wholeEq, errorOn);
                        textBox.setText(result);
                        CalculatorScreen.wholeEq = result;
                        equal = true;
                        break;
                    }

                    // check for "+/-" sign
                    else if(str.equals("+/-")) {
                        if(!numIn) break;
                        // CALL FUNCTION TO DO ABSOLUTE VALUE FROM THE TEXTBOX
                        result = plusminus(CalculatorScreen.wholeEq);
                        textBox.setText(result);
                        CalculatorScreen.wholeEq = result;
                        break;
                    }

                    // check for "percent(%)" sign
                    else if(str.equals("%")) {
                        if(!numIn) break;
                        // Call Function to divide Function to get %
                        result = getPercent(CalculatorScreen.wholeEq);
                        textBox.setText(result);
                        CalculatorScreen.wholeEq = result;
                        break;
                    }

                    else if(str.equals("(-)")) {
                        if(negative) break;
                        String last = CalculatorScreen.wholeEq;
                        if(last.isEmpty() || last.charAt(CalculatorScreen.wholeEq.length()-1) == ',') {
                            textBox.setText(textBox.getText() + "-");
                            CalculatorScreen.wholeEq += "-";
                            operator = true;
                            negative = true;
                            break;
                        }
                        break;
                    }

                    else if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                        if(equal) equal = false;
                        if(operator) break;
                        if(!numIn) break;

                        // check for "+" sign
                        if(str.equals("+")) {
                            textBox.setText(textBox.getText() + "+");
                            CalculatorScreen.wholeEq += ",+,";
                        }

                        // check for "-" sign
                        if(str.equals("-")) {
                            textBox.setText(textBox.getText() + "-");
                            CalculatorScreen.wholeEq += ",-,";
                        }

                        // check for "*" sign
                        else if(str.equals("*")) {
                            textBox.setText(textBox.getText() + "*");
                            CalculatorScreen.wholeEq += ",*,";
                        }

                        // check for "/" sign
                        else if(str.equals("/")) {
                            textBox.setText(textBox.getText() + "/");
                            CalculatorScreen.wholeEq += ",/,";
                        }
                        operator = true;
                        negative = false;
                        deci = false;
                        break;
                    }

                    // check for "." sign
                    else if(str.equals(".")) {
                        if(deci) break;
                        if(equal || !numIn) {
                            textBox.setText("0.");
                            CalculatorScreen.wholeEq = "0.";
                            operator = true;
                            deci = true;
                            equal = false;
                            break;
                        }
                        if(operator) break;
                        textBox.setText(textBox.getText() + ".");
                        CalculatorScreen.wholeEq += ".";

                        operator = true;
                        deci = true;
                        break;
                    }

                    // check for numerical sign
                    else if(str.equals(type[i])) {
                        if(equal) {
                            textBox.setText(type[i]);
                            CalculatorScreen.wholeEq = type[i];
                            equal = false;

                            break;
                        }
                        textBox.setText(textBox.getText() + type[i]);
                        CalculatorScreen.wholeEq += type[i];
                        operator = false;
                        numIn = true;
                        break;
                    }
                }
            }
        });
    }

    // get percentage --> * 100
    public String getPercent(String wholeEq) {
        double num = Double.parseDouble(wholeEq)/100;
        wholeEq = String.valueOf(num);
        BigDecimal temp = new BigDecimal(String.valueOf(wholeEq));
        DecimalFormat formatter = new DecimalFormat("0.##########");
        wholeEq = formatter.format(temp);
        return wholeEq;
    }

    // flip sign
    public String plusminus(String num) {
        double wholeEq = Double.parseDouble(num)*-1;
        num = String.valueOf(wholeEq);
        BigDecimal temp = new BigDecimal(String.valueOf(num));
        DecimalFormat formatter = new DecimalFormat("0.##########");
        num = formatter.format(temp);
        return num;
    }
}

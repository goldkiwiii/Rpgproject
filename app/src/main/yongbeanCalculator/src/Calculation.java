import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;

public class Calculation {

    // get Equal sign
    public String equalSign(String wholeEq, boolean errorOn) {
        Stack<String> st = new Stack<String>();
        String[] list = wholeEq.split(",");
        int precedence, currentIndex = 0;
        String[] list2 = new String[list.length];
        double temp = 0;
        BigDecimal temp2 = null;
        String result = "";

        System.out.println(Arrays.toString(list));

        if(!is_operand(list[list.length-1])) {
            errorOn = true;
            return "Finished with Operator. A/C to reset.";
        }

        // make stack for postfix
        st.push("$");
        for(int i = 0; i < list.length; i++) {
            if(is_operand(list[i])) {
                list2[currentIndex++] = list[i];
            }
            else {
                precedence = get_precedence(list[i]);
                if(precedence != 0) {
                    if(get_precedence(st.peek()) >= precedence){
                        list2[currentIndex++] = st.pop();
                        i--;
                    }
                    else{
                        st.push(list[i]);
                    }
                }
            }
        }

        while(!st.peek().equals("$")){
            list2[currentIndex++] = st.pop();
        }

        // calcualate from postfix
        for(int i = 0; i < list2.length; i++) {
            if(is_operand(list2[i])) st.push(list2[i]);
            else {
                double firstNum = Double.parseDouble(st.pop());
                double secondNum = Double.parseDouble(st.pop());
                if(list2[i].equals("+")) {
                    temp = (secondNum + firstNum);
                }
                else if(list2[i].equals("-")) {
                    temp = (secondNum - firstNum);
                }
                else if(list2[i].equals("*")) {
                    temp = (secondNum * firstNum);
                }
                else if(list2[i].equals("/")) {
                    if(firstNum == 0)  {
                        errorOn = true;
                        return "Argument divisor is 0. A/C to reset.";
                    }
                    temp = (secondNum / firstNum);

                }
                st.push(Double.toString(temp));
            }
        }
        result = st.pop();
        st.pop();
        System.out.println(result);
        temp2 = new BigDecimal(String.valueOf(result));
        DecimalFormat formatter = new DecimalFormat("0.##########");
        result = formatter.format(temp2);
        return result;
    }

    // check for is_operand
    public boolean is_operand(String list) {
        if((list.equals("+")) || (list.equals("-")) || (list.equals("*")) || (list.equals("/")) ||  (list.equals("$")))
            return false;
        else
            return true;
    }

    // get precedence level
    public int get_precedence(String op) {
        if((op.equals("+")) || (op.equals("-")))
            return 1;
        else if((op.equals("*")) || (op.equals("/")))
            return 2;
        return 0;
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

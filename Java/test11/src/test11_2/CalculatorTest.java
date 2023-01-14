package test11_2;

import java.util.ArrayList;
import java.util.Stack;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.solve("114+514*1919/180"));
    }
}

class Calculator {
    ArrayList<String> expression;
    Stack<Integer> numStack;
    Stack<String> operStack;
    int num1;
    int num2;
    String operation;

    private ArrayList<String> transferToList(String express) {
        ArrayList<String> expression = new ArrayList<>();
        int index = 0;
        String ch;
        String tempNum = "";
        while (index < express.length()) {
            ch = express.substring(index, index + 1);
            if (isOperation(ch)) {
                expression.add(ch);
            } else if (ch.matches("\\d")) {
                tempNum += ch;
                if (index == express.length() - 1 || !express.substring(index + 1, index + 2).matches("\\d")) {
                    expression.add(tempNum);
                    tempNum = "";
                }
            }
            index++;
        }
        return expression;
    }

    private boolean isOperation(String ch) {
        return ch.equals("+") || ch.equals("-") ||
               ch.equals("*") || ch.equals("/");
    }

    private int returnPriority(String ch) {
        if (ch.equals("*") || ch.equals("/")) {
            return 2;
        } else if (ch.equals("+") || ch.equals("-")) {
            return 1;
        } else {
            return -1;
        }
    }

    private int calculate(int num1, int num2, String operation) {
        switch (operation) {
        case "+":
            return num2 + num1;
        case "-":
            return num2 - num1;
        case "*":
            return num2 * num1;
        case "/":
            return num2 / num1;
        default:
            throw new RuntimeException("错误的运算符");
        }
    }

    public int solve(String express) {
        expression = transferToList(express);
        numStack = new Stack<Integer>();
        operStack = new Stack<String>();
        for (String item : expression) {
            if (item.matches("\\d+")) {
                numStack.push(Integer.valueOf(item));
            } else if (isOperation(item)) {
                if (operStack.empty()) {
                    operStack.push(item);
                } else {
                    if (returnPriority(item) <= returnPriority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operation = operStack.pop();
                        numStack.push(calculate(num1, num2, operation));
                        operStack.push(item);
                    } else {
                        operStack.push(item);
                    }
                }
            }
        }

        while (!operStack.empty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operStack.pop();
            numStack.push(calculate(num1, num2, operation));
        }
        return numStack.pop();
    }
}

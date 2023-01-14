package test11_2;

import java.util.ArrayList;
import java.util.Stack;

public class PolishCalculatorTest {
    public static void main(String[] args) {
        PolishCalculator polishCalculator = new PolishCalculator();
        System.out.println(polishCalculator.solve("(3+4)*7"));
    }
}

class PolishCalculator {
    Stack<Integer> numStack;
    ArrayList<String> reverseExpression;
    int num1;
    int num2;

    private ArrayList<String> transferToList(String express) {
        ArrayList<String> expression = new ArrayList<>();
        int index = 0;
        String ch;
        String tempNum = "";
        while (index < express.length()) {
            ch = express.substring(index, index + 1);
            if (isOperation(ch) || ch.equals("(") || ch.equals(")")) {
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

    private ArrayList<String> TransferToReverseList(ArrayList<String> expression) {
        Stack<String> operStack = new Stack<>();
        ArrayList<String> reverseExpression = new ArrayList<>();

        for (String item : expression) {
            if (item.matches("\\d+")) {
                reverseExpression.add(item);
            } else if (item.equals("(") ||
                       (isOperation(item) &&
                        (operStack.empty() || operStack.peek().equals("(") || returnPriority(item) > returnPriority(operStack.peek())))) {
                operStack.push(item);
            } else if (item.equals(")")) {
                while (!operStack.peek().equals("(")) {
                    reverseExpression.add(operStack.pop());
                }
                operStack.pop();
            } else if (isOperation(item) && returnPriority(item) <= returnPriority(operStack.peek())) {
                while (!(operStack.empty() || operStack.peek().equals("(") || returnPriority(item) > returnPriority(operStack.peek()))) {
                    reverseExpression.add(operStack.pop());
                }
                operStack.push(item);
            }
        }

        while (!operStack.empty()) {
            reverseExpression.add(operStack.pop());
        }

        return reverseExpression;
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
        reverseExpression = TransferToReverseList(transferToList(express));
        numStack = new Stack<Integer>();
        for (String item : reverseExpression) {
            if (item.matches("\\d+")) {
                numStack.push(Integer.parseInt(item));
            } else if (isOperation(item)) {
                num1 = numStack.pop();
                num2 = numStack.pop();
                numStack.push(calculate(num1, num2, item));
            }
        }
        return numStack.pop();
    }
}

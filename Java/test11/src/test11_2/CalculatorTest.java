package test11_2;

import java.util.ArrayList;
import java.util.Stack;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.solve("4+3*7"));
    }
}

class Calculator {
    //用集合表示的中缀表达式
    ArrayList<String> expression;
    //数栈
    Stack<Integer> numStack;
    //符号栈
    Stack<String> operStack;
    //需要使用的一些临时变量
    int num1;
    int num2;
    String operation;

    //将传入的字符串形式的中缀表达式转换成用集合表示的中缀表达式
    private ArrayList<String> transferToList(String express) {
        ArrayList<String> expression = new ArrayList<>();
        int index = 0;
        String ch;
        String tempNum = "";
        while (index < express.length()) {
            //依次扫描每个字符
            ch = express.substring(index, index + 1);
            //如果是操作符，则直接加入集合中
            if (isOperation(ch)) {
                expression.add(ch);
            }
            //如果是数字，就要考虑多位数的存在，因此需要进行字符串拼接
            else if (ch.matches("\\d")) {
                tempNum += ch;
                //如果遍历结束，或者下一个字符不是数字，则拼接结束，将拼接后的数字加入集合中
                if (index == express.length() - 1 || !express.substring(index + 1, index + 2).matches("\\d")) {
                    expression.add(tempNum);
                    tempNum = "";
                }
            }
            index++;
        }
        return expression;
    }

    //判断是否为操作符
    private boolean isOperation(String ch) {
        return ch.equals("+") || ch.equals("-") ||
               ch.equals("*") || ch.equals("/");
    }

    //返回操作符的优先级
    private int returnPriority(String ch) {
        if (ch.equals("*") || ch.equals("/")) {
            return 2;
        } else if (ch.equals("+") || ch.equals("-")) {
            return 1;
        } else {
            return -1;
        }
    }

    //对传入的两个数字和一个操作符进行运算
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

    //主要方法，调用该方法，并传入一个字符串形式的中缀表达式，就能返回结果
    public int solve(String express) {
        //先将字符串形式的中缀表达式转换为集合形式
        expression = transferToList(express);
        numStack = new Stack<Integer>();
        operStack = new Stack<String>();
        //开始遍历
        for (String item : expression) {
            if (item.matches("\\d+")) {
                numStack.push(Integer.valueOf(item));
            } else if (isOperation(item)) {
                if (operStack.empty()) {
                    operStack.push(item);
                } else if (returnPriority(item) <= returnPriority(operStack.peek())) {
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
        //遍历结束，开始处理栈中剩余的元素
        while (!operStack.empty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operation = operStack.pop();
            numStack.push(calculate(num1, num2, operation));
        }
        //返回结果
        return numStack.pop();
    }
}

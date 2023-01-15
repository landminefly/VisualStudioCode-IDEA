package test11_2;

import java.util.ArrayList;
import java.util.Stack;

public class PolishCalculatorTest {
    public static void main(String[] args) {
        PolishCalculator polishCalculator = new PolishCalculator();
        System.out.println(polishCalculator.solve(" ( 4 + 4 ) * 7 "));
    }
}

class PolishCalculator {
    //用集合表示的中缀表达式
    Stack<Integer> numStack;
    //用集合表示的后缀表达式
    ArrayList<String> reverseExpression;
    //需要使用的一些临时变量
    int num1;
    int num2;

    ////将传入的字符串形式的中缀表达式转换成用集合表示的中缀表达式
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

    //将集合形式的中缀表达式转换成集合形式的后缀表达式
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
                //注意：左右括号都不会加入集合reverseExpression
                operStack.pop();
            }
            //这个else其实省略了条件 isOperation(item) && returnPriority(item) <= returnPriority(operStack.peek())
            //因为item既不是括号，也不是数字，只能是操作符
            //而item作为操作符时，已经排除了前三种情况，因此只能是最后一种情况
            else {
                //循环从operStack中pop出栈顶操作符，并将其加入集合reverseExpression，直到符合前三种情况之一为止，并将当前操作符入栈operStack
                while (!(operStack.empty() || operStack.peek().equals("(") || returnPriority(item) > returnPriority(operStack.peek()))) {
                    reverseExpression.add(operStack.pop());
                }
                operStack.push(item);
            }
        }

        //遍历结束，开始处理operStack中的剩余元素
        while (!operStack.empty()) {
            reverseExpression.add(operStack.pop());
        }

        return reverseExpression;
    }

    //判断是否为操作符（括号不算，单独考虑）
    private boolean isOperation(String ch) {
        return ch.equals("+") || ch.equals("-") ||
               ch.equals("*") || ch.equals("/");
    }

    //返回操作符的优先级（括号不算）
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
        //先将字符串形式中缀表达式转换为集合形式，再将其转换为集合形式的后缀表达式
        reverseExpression = TransferToReverseList(transferToList(express));
        numStack = new Stack<Integer>();
        //开始遍历
        for (String item : reverseExpression) {
            if (item.matches("\\d+")) {
                numStack.push(Integer.parseInt(item));
            } else if (isOperation(item)) {
                num1 = numStack.pop();
                num2 = numStack.pop();
                numStack.push(calculate(num1, num2, item));
            }
        }
        //遍历结束后，numStack 中只会剩下一个数字，这个数字就是最终的计算结果
        return numStack.pop();
    }
}

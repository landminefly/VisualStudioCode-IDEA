package test11_2;

public class ArrayStackTest {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.pop();
        stack.push(1203);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.showAll();
    }
}

class ArrayStack {
    private final int maxSize;
    private final int[] stack;
    //栈底bottom的位置固定不变，可以认为它永远是-1，因此可以不管它
    //栈顶top则应该初始化为-1，表示此时栈为空
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        top = -1;
    }

    //判断是否栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断是否栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满，入栈失败");
        }
        stack[++top] = value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，出栈失败");
        }
        return stack[top--];
    }

    //查看位于栈顶的元素，但不出栈
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈空，peek失败");
        }
        return stack[top];
    }

    //按顺序打印栈的所有元素
    public void showAll(){
        if(isEmpty()){
            System.out.println("栈空");
        }
        //注意：要从栈顶开始遍历
        for(int i = top; i>=0 ; i--)
        {
            System.out.println(stack[i]);
        }
    }
}

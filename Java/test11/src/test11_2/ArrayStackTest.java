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
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        top = -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满，入栈失败");
        }
        stack[++top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，出栈失败");
        }
        return stack[top--];
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈空，peek失败");
        }
        return stack[top];
    }

    public void showAll(){
        if(isEmpty()){
            System.out.println("栈空");
        }
        for(int i = top; i>=0 ; i--)
        {
            System.out.println(stack[i]);
        }
    }
}

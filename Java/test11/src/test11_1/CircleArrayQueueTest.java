package test11_1;

public class CircleArrayQueueTest {
    public static void main(String[] args) {
        CircleArrayQueue c = new CircleArrayQueue(5);
        try {
            c.addIntoQueue(10);
            c.addIntoQueue(20);
            c.addIntoQueue(30);
            c.addIntoQueue(40);
            c.leaveQueue();
            c.leaveQueue();
            c.addIntoQueue(50);
            c.addIntoQueue(60);
            c.showAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//这里默认数组类型是int，如有需要可以改成泛型
class CircleArrayQueue {
    private final int maxArraySize;
    private int front = 0;
    private int rear = 0;
    private final int[] array;

    //构造器
    public CircleArrayQueue(int maxArraySize) {
        this.maxArraySize = maxArraySize;
        this.array = new int[maxArraySize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxArraySize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //返回队列中元素个数
    public int count() {
        return (rear + maxArraySize - front) % maxArraySize;
    }

    //入列
    public void addIntoQueue(int value) throws Exception {
        if (isFull()) {
            throw new Exception("队列已满！");
        }
        array[rear] = value;
        rear = (rear + 1) % maxArraySize;
    }

    //出列
    public int leaveQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空！");
        }
        int returnValue = array[front];
        front = (front + 1) % maxArraySize;
        return returnValue;
    }

    //按顺序打印队列中所有元素
    public void showAll() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + count(); i++) {
            //注意此处 i 需要取模
            System.out.println(array[i % maxArraySize]);
        }
    }
}
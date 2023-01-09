package test12_1;

public class CircleArrayQueueTest
{
    public static void main(String[] args)
    {
        CircleArrayQueue c = new CircleArrayQueue(5);

        try
        {
            c.addIntoQueue(1);
            c.addIntoQueue(2);
            c.addIntoQueue(3);
            c.leaveQueue();
            c.addIntoQueue(4);
            c.addIntoQueue(5);
            c.showAll();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class CircleArrayQueue
{
    private final int maxArraySize;
    private int front = 0;
    private int rear = 0;
    private final int[] array;

    public CircleArrayQueue(int maxArraySize)
    {
        this.maxArraySize = maxArraySize;
        this.array = new int[maxArraySize];
    }

    public boolean isFull()
    {
        return (rear + 1) % maxArraySize == front;
    }

    public boolean isEmpty()
    {
        return front == rear;
    }

    public void addIntoQueue(int value) throws Exception
    {
        if (isFull())
        {
            throw new Exception("队列已满！");
        }
        array[rear] = value;
        rear = (rear + 1) % maxArraySize;
    }

    public int leaveQueue() throws Exception
    {
        if (isEmpty())
        {
            throw new Exception("队列为空！");
        }
        int returnValue = array[front];
        front = (front + 1) % maxArraySize;
        return returnValue;
    }

    public void showAll()
    {
        if (isEmpty())
        {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + (rear + maxArraySize - front) % maxArraySize; i++)
        {
            System.out.println(array[i % maxArraySize]);
        }
    }
}
package test4_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Show1 implements Callable
{
    @Override
    public Object call()
    {
        int sum = 0;
        for(int i = 0; i<=100; i++)
        {
            sum += i;
        }
        return sum;
    }
}
public class TreadTest3
{
    public static void main(String[] args)
    {
        Callable c = new Show1();
        FutureTask f = new FutureTask(c);
        Thread t = new Thread(f);
        t.start();
        try
        {
            int sum = (Integer)f.get();
            System.out.println(sum);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

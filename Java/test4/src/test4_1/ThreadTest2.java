package test4_1;

// class MyThread1 implements Runnable
// {
//     @Override
//     public void run()
//     {
//         for (int i = 0; i < 5000; i++)
//         {
//             if (i % 2 == 0)
//                 System.out.println(Thread.currentThread().getName() + ":" + i);
//         }
//     }
// }
// public class ThreadTest2
// {
//     public static void main(String[] args)
//     {
//         MyThread1 m = new MyThread1();
//         Thread t = new Thread(m);
//         t.start();
//     }
// }

import java.util.concurrent.locks.ReentrantLock;

class Window implements Runnable
{
    private int ticket = 300;
    //实例化ReentrantLock类
    private ReentrantLock lock = new ReentrantLock(true);
    //该构造器的参数若为true，则保证按照线程排队顺序来执行且同一个线程不会执行连续两次；若为false或空参，则没有这个保证
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                lock.lock();//调用lock
                if (ticket > 0)
                {
                    System.out.println(Thread.currentThread().getName() + "卖出，票号：" + ticket);
                    ticket--;
                }else
                {
                    return;
                }
            } finally
            {
                lock.unlock();//使用try-finally保证unlock一定被调用
                              //否则调用lock往下的代码都是单线程了
            }

        }
    }
}
// public class ThreadTest2
// {
//     public static void main(String[] args)
//     {
//         Window w = new Window();
//         Thread t1 = new Thread(w);
//         Thread t2 = new Thread(w);
//         Thread t3 = new Thread(w);
//         t1.setName("窗口一");
//         t2.setName("窗口二");
//         t3.setName("窗口三");
//         t2.start();
//         t1.start();
//         t3.start();
//     }
// }

// class Singleton
// {
//     private Singleton()
//     {}
//     private static Singleton s = null;
//     public static Singleton getS()
//     {
//         // //方式二：效率稍差
//         // synchronized (Singleton.class)
//         // {
//         //     if(s == null)
//         //         s = new Singleton();
//         //     return s;
//         // }
//
//         //方式三：效率较好，后到的线程不用排队
//         if(s == null)
//         synchronized (Singleton.class)
//         {
//             if(s == null)
//                 s = new Singleton();
//         }
//         return s;
//     }
//
// }

class Show implements Runnable
{
    private int number = 0;
    @Override
    public void run()
    {
        while(true)
        {
            synchronized (this)
            {
                notify();
                if(number <= 100)
                {
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try
                    {
                        wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    break;
            }
        }
    }
}

class Test
{
    public static void main(String[] args)
    {
        Show s = new Show();
        Thread t1 = new Thread(s,"线程一");
        Thread t2 = new Thread(s,"线程二");
        t1.start();
        t2.start();
    }
}

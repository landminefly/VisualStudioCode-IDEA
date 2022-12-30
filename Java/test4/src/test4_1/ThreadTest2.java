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
    //ʵ����ReentrantLock��
    private ReentrantLock lock = new ReentrantLock(true);
    //�ù������Ĳ�����Ϊtrue����֤�����߳��Ŷ�˳����ִ����ͬһ���̲߳���ִ���������Σ���Ϊfalse��ղΣ���û�������֤
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                lock.lock();//����lock
                if (ticket > 0)
                {
                    System.out.println(Thread.currentThread().getName() + "������Ʊ�ţ�" + ticket);
                    ticket--;
                }else
                {
                    return;
                }
            } finally
            {
                lock.unlock();//ʹ��try-finally��֤unlockһ��������
                              //�������lock���µĴ��붼�ǵ��߳���
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
//         t1.setName("����һ");
//         t2.setName("���ڶ�");
//         t3.setName("������");
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
//         // //��ʽ����Ч���Բ�
//         // synchronized (Singleton.class)
//         // {
//         //     if(s == null)
//         //         s = new Singleton();
//         //     return s;
//         // }
//
//         //��ʽ����Ч�ʽϺã��󵽵��̲߳����Ŷ�
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
        Thread t1 = new Thread(s,"�߳�һ");
        Thread t2 = new Thread(s,"�̶߳�");
        t1.start();
        t2.start();
    }
}

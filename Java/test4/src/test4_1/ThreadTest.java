package test4_1;

// public class ThreadTest
// {
//     public static void main(String[] args)
//     {
//         Test01 t1 = new Test01();
//         //����start�����ã�������һ���µ��߳� �ڵ��ø��̵߳�run()
//         t1.start();
//
//         //����Ĵ���������main�߳���ִ�е�
//         for(int i = 0; i<50000; i++)
//         {
//             if(i % 2 == 0)
//             System.out.println(Thread.currentThread().getName()+":"+i);
//         }
//     }
// }
// class Test01 extends Thread
// {
//     @Override
//     public void run()
//     {
//         for(int i = 0; i<50000; i++)
//         {
//             if(i % 2 == 0)
//             System.out.println(Thread.currentThread().getName()+":"+i);
//         }
//     }
// }
class MyThread extends Thread
{
    public MyThread(String name)
    {
        super(name);
    }
    public MyThread()
    {

    }

    @Override
    public void run()
    {
        for (int i = 0; i < 5000; i++)
        {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + ":" + i);

            if(i % 20 == 0)
                this.yield();//this.����ʡ
        }
    }
}
public class ThreadTest
{
    public static void main(String[] args) throws InterruptedException
    {
        MyThread t1 = new MyThread();
        t1.setName("�߳�1");//�����ڸ��߳�startǰ��������
        t1.start();

        for (int i = 0; i < 5000; i++)
        {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        System.out.println(t1.isAlive());
    }
}
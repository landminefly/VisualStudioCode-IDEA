package test4_1;

// public class ThreadTest
// {
//     public static void main(String[] args)
//     {
//         Test01 t1 = new Test01();
//         //调用start的作用：①启动一个新的线程 ②调用该线程的run()
//         t1.start();
//
//         //下面的代码仍是在main线程中执行的
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
                this.yield();//this.不能省
        }
    }
}
public class ThreadTest
{
    public static void main(String[] args) throws InterruptedException
    {
        MyThread t1 = new MyThread();
        t1.setName("线程1");//必须在该线程start前给其命名
        t1.start();

        for (int i = 0; i < 5000; i++)
        {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        System.out.println(t1.isAlive());
    }
}
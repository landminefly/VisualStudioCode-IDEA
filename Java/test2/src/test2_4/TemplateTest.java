package test2_4;

public class TemplateTest
{
    public static void main(String[] args)
    {
        Template t = new SubTemplate();
        t.spendTime();
    }
}

abstract class Template
{
    public void spendTime()
    {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("所需时间："+(end - start)+"毫秒");
    }
    public abstract void code();
}

class SubTemplate extends Template
{
    @Override
    public void code()
    {
        int count = 0;
        for(int i = 2; i<10000; i++)
        {
            boolean flag = true;
            for(int j = 2; j<=Math.sqrt(i); j++)
            {
                if(i % j == 0)

                {
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                count++;
            }
        }
        System.out.println("质数共有 "+count+" 个");
    }
}

// interface AA
// {
//     void A();
// }
// interface BB
// {
//     void B();
// }
// interface CC extends AA,BB
// {
//
// }

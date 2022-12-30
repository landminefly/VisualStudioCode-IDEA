package test9_1;

import org.junit.jupiter.api.Test;

public class ReviewTest_2
{
    public static void main(String[] args)
    {
        ReviewTest_2 reviewTest_2 = new ReviewTest_2();
        reviewTest_2.show();
    }
    public void show()
    {
        System.out.println(1);
        main(new String[]{"123"}); //StackOverFlow
    }

    @Test
    public void test1()
    {
        Integer integer = Integer.valueOf(12);
        int i = integer.intValue();
        System.out.println(i);
    }

    @Test
    public void test2()
    {
        new C().test();
    }
}

class C extends B implements A
{
    public void test()
    {
        method();
    }
}

interface A
{
    default void method()
    {
        System.out.println("aaa");
    }
}
class B
{
    public void method(int a)
    {
        System.out.println("bbb");

    }
}

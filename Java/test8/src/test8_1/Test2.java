package test8_1;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test2
{
    public static void main(String[] args)
    {
        System.out.println(Math.sin(Math.PI / 2));
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        Iterator<Integer> iterator = arrayList.iterator();
        System.out.println(iterator.next());
    }

    @Test
    public void test1()
    {
        List<String> list = Arrays.asList("操", "你");
        List<String> list1 = new ArrayList<>();
        list1.add("妈");
        System.out.println(list1);
        System.out.println(list);
    }

    @Test
    public void test2()
    {
        int a = 2;
        int b = 4;
        int k = 0;
        A:for(int i = 1; i<=b; i++)
        {
            for(int j = 1; j<=a; j++)
            {
                if(i*a == j*b)
                {
                    k = i*a;
                    break A;
                }
            }
        }
        System.out.println(k);
    }
}

class A
{
    static void f()
    {
        System.out.println("111");
    }
}

class B extends A
{
    static void f()
    {
        System.out.println("222");
    }
}

class ATest
{
    public static void main(String[] args)
    {
        A a = new B();
        a.f();
    }
}
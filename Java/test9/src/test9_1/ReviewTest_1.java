package test9_1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ReviewTest_1
{
    @Test
    public void test1()
    {
        ReviewTest_1 reviewTest_1 = new ReviewTest_1();
        System.out.println(reviewTest_1);
    }

    @Test
    public void test2()
    {
        int a = 1;
        int b = 3;
        System.out.println(++a < 3 || ++b <5);
        System.out.println(a+" "+b);
    }

    @Test
    public void test3()
    {
        int a = 1;
        System.out.println(a);
        System.out.println(a<<1);
        System.out.println(a<<2);
        System.out.println(a<<3);
        System.out.println((a<<3)>>1);
        System.out.println(Math.pow(a<<2,0.5));
    }

    @Test
    public void test4()
    {
        int[][] ints1 = new int[][]{{1,2},{3,4}};
        System.out.println(Arrays.toString(ints1));
        System.out.println(Arrays.deepToString(ints1));

        float[][] floats = new float[3][];
        System.out.println(Arrays.deepToString(floats));
    }

    @Test
    public void test5()
    {
        People people1 = new People();
        System.out.println(people1.age);
        People xijinping = new People(63, new String("Xijinping"));
        System.out.println(xijinping.name+","+xijinping.age);
    }

    @Test
    public void test6()
    {
        int a = 100 / 6;
    }
}

class People
{
    int age;
    String name;

    public People(){}

    public People(int age)
    {
        this.age = age;
    }

    public People(int age, String name)
    {
        this(age);
        this.name = name;
    }

    public void set(int age)
    {
        this.age = age;
    }
}

class Man extends People
{
    double lengthOfDick;
    public void set(String name)
    {
        super.name = name;
    }
    public void set(Double lengthOfDick)
    {
        this.lengthOfDick = lengthOfDick;
    }
}


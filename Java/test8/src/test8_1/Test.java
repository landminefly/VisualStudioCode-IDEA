package test8_1;

public class Test
{
    public static void main(String[] args)
    {
        Demo d1 = new Demo();
        d1.temp = "World";
        System.out.println(d1.temp);
        fun(d1);
        System.out.println(d1.temp);

    }
    public static void fun(Demo d)
    {
        d.temp = "MLDN";
    }
}

class Demo
{
    String temp = "hello";
}

class StringTest
{
    String str = new String("good");
    char[] chars = { 't', 'e', 's', 't' };

    public void change(String str, char[] chars)
    {
        str = "test ok";
        chars[0] = 'b';
    }
    public static void main(String[] args)
    {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.chars);
        System.out.println(ex.str);//good
        System.out.println(ex.chars);//best
    }
}
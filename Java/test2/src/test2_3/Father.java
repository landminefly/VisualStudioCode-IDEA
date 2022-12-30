package test2_3;

class Father
{
    static{
        System.out.println("111111");
    }
    {
        System.out.println("222222");
    }
    public Father()
    {
        super();
        System.out.println("333333");
    }
}
class Son extends Father
{
    static{
        System.out.println("444444");
    }
    {
        System.out.println("555555");
    }
    public Son ()
    {
        super();
        System.out.println("666666");
    }
    public Son(String name)
    {
        this();
        System.out.println("777777");
    }

    public static void main(String[] args)
    {
        System.out.println("888888");
        System.out.println("************");
        new Son("Nobody");
        System.out.println("************");
        new Father();
    }
}

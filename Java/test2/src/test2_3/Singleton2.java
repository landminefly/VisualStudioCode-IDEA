package test2_3;

class Singleton2
{
    //1.私有化类的构造器
    private Singleton2()
    {}
    //2.声明类的对象并置空
    private static Singleton2 s = null;
    //3.声明public的静态方法，第一次使用时创建对象，每次都返回该对象
    public static Singleton2 getS()
    {
        if(s == null)
            s = new Singleton2();
        return s;
    }
}

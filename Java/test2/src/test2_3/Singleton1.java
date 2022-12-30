package test2_3;

class Singleton1
{
    //1.私有化类的构造器
    private Singleton1()
    {}
    //2.内部创建类的对象
    private static Singleton1 s = new Singleton1();
    //3.提供public的静态方法，返回类的对象
    public static Singleton1 getS()
    {
        return s;
    }
}

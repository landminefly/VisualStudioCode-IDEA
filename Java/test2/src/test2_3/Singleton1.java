package test2_3;

class Singleton1
{
    //1.˽�л���Ĺ�����
    private Singleton1()
    {}
    //2.�ڲ�������Ķ���
    private static Singleton1 s = new Singleton1();
    //3.�ṩpublic�ľ�̬������������Ķ���
    public static Singleton1 getS()
    {
        return s;
    }
}

package test2_3;

class Singleton2
{
    //1.˽�л���Ĺ�����
    private Singleton2()
    {}
    //2.������Ķ����ÿ�
    private static Singleton2 s = null;
    //3.����public�ľ�̬��������һ��ʹ��ʱ��������ÿ�ζ����ظö���
    public static Singleton2 getS()
    {
        if(s == null)
            s = new Singleton2();
        return s;
    }
}

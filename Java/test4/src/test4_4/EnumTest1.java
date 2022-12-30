package test4_4;

public class EnumTest1
{
    public static void main(String[] args)
    {
        Asoul diana = Asoul.DIANA;
        System.out.println(diana);
    }
}

class Asoul
{
    //1.����Asoul���������:����Ϊprivate final
    private final String CNName;
    private final String ENName;

    //2.˽�л���Ĺ�����,�����������Ը�ֵ
    private Asoul(String CNName, String ENName)
    {
        this.CNName = CNName;
        this.ENName = ENName;
    }

    //3.�ṩ��ǰö����Ķ����������Ϊpublic static final
    public static final Asoul DIANA = new Asoul("��Ȼ","Diana");
    public static final Asoul AVA = new Asoul("����","Ava");
    public static final Asoul BELLA = new Asoul("����","Bella");

    //4.��������1����ȡö������������
    public String getCNName()
    {
        return CNName;
    }
    public String getENName()
    {
        return ENName;
    }

    //4.��������2���ṩtoString()
    @Override
    public String toString()
    {
        return "Asoul{" +
                "CNName='" + CNName + '\'' +
                ", ENName='" + ENName + '\'' +
                '}';
    }
}

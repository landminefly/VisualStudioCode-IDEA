package test4_4;

public class EnumTest2
{
    public static void main(String[] args)
    {
        EnumAsoul ava = EnumAsoul.AVA;
        System.out.println(ava.getCNName());
        System.out.println(ava.getENName());

        //toString():����ö������������
        System.out.println(ava.toString());

        //values():�������е�ö������󹹳ɵ�����
        EnumAsoul[] values = EnumAsoul.values();
        for(EnumAsoul a : values)
        {
            System.out.println(a);
        }

        //valueOf(String objName):����ö�����ж�������objName�Ķ���
        //���û��objName��ö������������쳣��IllegalArgumentException
        EnumAsoul bella = EnumAsoul.valueOf("BELLA");

        ava.show();
    }
}

enum EnumAsoul implements Show
{
    //1.�����ṩ��ǰö����Ķ��󣬶������֮����","��������";"����
    DIANA("��Ȼ","Diana")
            {
                public void show()
                {
                    System.out.println("ȻȻ��һ��");
                }
            },
    AVA("����","Ava")
            {
                public void show()
                {
                    System.out.println("�����һ��");
                }
            },

    BELLA("����","Bella")
            {
                public void show()
                {
                    System.out.println("�����һ��");
                }
            };

    //2.����EnumAsoul���������:private final����
    private final String CNName;
    private final String ENName;

    //2.˽�л���Ĺ�����,�����������Ը�ֵ
    EnumAsoul(String CNName, String ENName)
    {
        this.CNName = CNName;
        this.ENName = ENName;
    }

    //4.��������1����ȡö������������
    public String getCNName()
    {
        return CNName;
    }
    public String getENName()
    {
        return ENName;
    }

    //4.��������2��enum�Զ���д��toString(),������Ҫ���ٴ���д
}


interface Show
{
    void show();
}

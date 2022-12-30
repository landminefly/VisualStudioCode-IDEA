package test4_4;

public class EnumTest2
{
    public static void main(String[] args)
    {
        EnumAsoul ava = EnumAsoul.AVA;
        System.out.println(ava.getCNName());
        System.out.println(ava.getENName());

        //toString():返回枚举类对象的名称
        System.out.println(ava.toString());

        //values():返回所有的枚举类对象构成的数组
        EnumAsoul[] values = EnumAsoul.values();
        for(EnumAsoul a : values)
        {
            System.out.println(a);
        }

        //valueOf(String objName):返回枚举类中对象名是objName的对象。
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
        EnumAsoul bella = EnumAsoul.valueOf("BELLA");

        ava.show();
    }
}

enum EnumAsoul implements Show
{
    //1.首先提供当前枚举类的对象，多个对象之间用","隔开，以";"结束
    DIANA("嘉然","Diana")
            {
                public void show()
                {
                    System.out.println("然然第一！");
                }
            },
    AVA("向晚","Ava")
            {
                public void show()
                {
                    System.out.println("晚晚第一！");
                }
            },

    BELLA("贝拉","Bella")
            {
                public void show()
                {
                    System.out.println("拉姐第一！");
                }
            };

    //2.声明EnumAsoul对象的属性:private final修饰
    private final String CNName;
    private final String ENName;

    //2.私有化类的构造器,并给对象属性赋值
    EnumAsoul(String CNName, String ENName)
    {
        this.CNName = CNName;
        this.ENName = ENName;
    }

    //4.其他诉求1：获取枚举类对象的属性
    public String getCNName()
    {
        return CNName;
    }
    public String getENName()
    {
        return ENName;
    }

    //4.其他诉求2：enum自动重写了toString(),若有需要可再次重写
}


interface Show
{
    void show();
}

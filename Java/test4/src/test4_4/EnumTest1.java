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
    //1.声明Asoul对象的属性:声明为private final
    private final String CNName;
    private final String ENName;

    //2.私有化类的构造器,并给对象属性赋值
    private Asoul(String CNName, String ENName)
    {
        this.CNName = CNName;
        this.ENName = ENName;
    }

    //3.提供当前枚举类的多个对象：声明为public static final
    public static final Asoul DIANA = new Asoul("嘉然","Diana");
    public static final Asoul AVA = new Asoul("向晚","Ava");
    public static final Asoul BELLA = new Asoul("贝拉","Bella");

    //4.其他诉求1：获取枚举类对象的属性
    public String getCNName()
    {
        return CNName;
    }
    public String getENName()
    {
        return ENName;
    }

    //4.其他诉求2：提供toString()
    @Override
    public String toString()
    {
        return "Asoul{" +
                "CNName='" + CNName + '\'' +
                ", ENName='" + ENName + '\'' +
                '}';
    }
}

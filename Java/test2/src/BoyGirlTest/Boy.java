package BoyGirlTest;

public class Boy
{
    private String name;
    private int age;

    public Boy(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return this.age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }

    public void marry(Girl girl)
    {
        if(this.age <22)
        {
            System.out.println("��̸̸������");
            return;
        }
        System.out.println(this.name+"˵������Ȣ"+girl.getName());
        //��׷Ů����ɽ��Ů������������Ӧ
    }
}

package BoyGirlTest;

public class Girl
{
    private String name;
    private int age;

    public Girl(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
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

    public void marry(Boy boy)
    {
        if(this.age <20)
        {
            System.out.println("��̸̸������");
            return;
        }
        System.out.println(this.name+"˵������޸�"+boy.getName());
        boy.marry(this);
        //Ů׷�и���ɴ���з���������Ӧ
    }
}

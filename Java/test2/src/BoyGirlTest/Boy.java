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
            System.out.println("先谈谈恋爱吧");
            return;
        }
        System.out.println(this.name+"说：我想娶"+girl.getName());
        //男追女隔座山，女方不会立即回应
    }
}

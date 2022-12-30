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
            System.out.println("先谈谈恋爱吧");
            return;
        }
        System.out.println(this.name+"说：我想嫁给"+boy.getName());
        boy.marry(this);
        //女追男隔层纱，男方会立即回应
    }
}

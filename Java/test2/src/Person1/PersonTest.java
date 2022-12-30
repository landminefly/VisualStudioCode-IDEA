package Person1;

public class PersonTest
{
    public static void main(String[] args)
    {
        Person p = new Man();
        Man m = null;
        //向下转型
        if(p instanceof Man)//true
        {
            m = (Man)p;
        }

        if(m instanceof Person)//此时也为true
        {
            System.out.println("dont know what to say");
        }
    }
}

class Person
{
    public void eat()
    {
        System.out.println("吃饭");
    }
}

class Man extends Person
{
    public void earn()
    {
        System.out.println("挣钱");
    }
}

class Woman extends Person
{
    public void cost()
    {
        System.out.println("花钱");
    }
}



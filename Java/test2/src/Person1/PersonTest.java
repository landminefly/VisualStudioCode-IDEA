package Person1;

public class PersonTest
{
    public static void main(String[] args)
    {
        Person p = new Man();
        Man m = null;
        //����ת��
        if(p instanceof Man)//true
        {
            m = (Man)p;
        }

        if(m instanceof Person)//��ʱҲΪtrue
        {
            System.out.println("dont know what to say");
        }
    }
}

class Person
{
    public void eat()
    {
        System.out.println("�Է�");
    }
}

class Man extends Person
{
    public void earn()
    {
        System.out.println("��Ǯ");
    }
}

class Woman extends Person
{
    public void cost()
    {
        System.out.println("��Ǯ");
    }
}



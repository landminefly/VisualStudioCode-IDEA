package test2_2;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class test2_2_1
{
    @Test
    public void test()
    {
        // Integer in = new Integer(12);
        // int i = in.intValue();

        // int i = 10;
        // String s1 = i + "";
        // //or
        // String s2 = String.valueOf(i);

        // String s = "123";
        // Integer in = new Integer(s);

        // String s1 = "tRuE";
        // boolean b1 = Boolean.parseBoolean(s1);//true
        // System.out.println(b1);
        //
        // String s2 = "true1";
        // boolean b2 = Boolean.parseBoolean(s2);//false

        // int num = 10;
        // Integer in = num;//自动装箱

        Integer in = new Integer(10);
        int num = in;//自动拆箱
    }

    public static void main(String[] args)
    {
        Person p = new Person("Nobody",'1',32);

        System.out.println(p);
        System.out.println(p.toString());//二者等价
    }
}

class Person
{
    String name;
    char gender;
    int age;

    public void eat()
    {
        System.out.println("eat");
    }

    public Person()
    {
    }

    public Person(String name, char gender, int age)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return gender == person.gender && age == person.age && name.equals(person.name);
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}

class Boy extends Person
{
}

class Girl extends Person
{
}

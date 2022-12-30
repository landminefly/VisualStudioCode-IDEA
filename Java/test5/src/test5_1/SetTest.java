package test5_1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class SetTest
{
    @Test
    public void test()
    {
        HashSet hashSet = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        hashSet.add(p1);
        hashSet.add(p2);

        p1.name = "CC";
        hashSet.remove(p1);
        System.out.println(hashSet);

        hashSet.add(new Person(1001,"CC"));
        System.out.println(hashSet);

        hashSet.add(new Person(1001,"AA"));
        System.out.println(hashSet);
    }
}

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return name.equals(person.name);
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
package test5_2;

public class Person
{
    String name;
    int id;

    public Person()
    {
    }

    public Person(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

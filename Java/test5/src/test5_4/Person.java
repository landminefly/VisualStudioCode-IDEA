package test5_4;

//包含了一堆结构的类，用来测试反射Reflection
@MyAnnotation(value="hi")
public class Person extends Creature<String> implements Comparable<String>,MyInterface
{
    private String name;
    static int age;
    public int id;

    @MyAnnotation(value="abc")
    public Person()
    {}

    private Person(String name)
    {
        this.name = name;
    }

    Person(String name,int id)
    {
        this.name = name;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    @MyAnnotation
    private double showHeight(double height)
    {
        System.out.println("My height is " + height + "kg.");
        return height;
    }

    public String showInterests(String interests,int years) throws NullPointerException,ClassCastException
    {
        return interests + " in " + years + " years old.";
    }

    private static void showFeature()
    {
        System.out.println("I am cute.");
    }

    @Override
    public void info()
    {
        System.out.println("I am a person.");
    }

    @Override
    public int compareTo(String o)
    {
        return 0;
    }

    @Override
    public void eat()
    {
        System.out.println("People do not eat themselves.");
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}

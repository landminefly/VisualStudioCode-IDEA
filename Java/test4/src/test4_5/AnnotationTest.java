package test4_5;

public class AnnotationTest
{
    public static void main(String[] args)
    {

    }
}

class Person
{
    String name;

    public Person(String name)
    {
        this.name = name;
    }

    public void doing()
    {
        System.out.println("Working...");
    }
}

@MyAnnotation("aaa")
@MyAnnotation("bbb")
@MyAnnotation("ccc")
class Student extends Person
{
    int studentID;

    public Student(String name, int studentID)
    {
        super(name);
        this.studentID = studentID;
    }

    @Override
    public void doing()
    {
        System.out.println("Studying...");
    }
}


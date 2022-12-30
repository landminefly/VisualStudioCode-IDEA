package test8_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main5
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String buffer;
        ArrayList<Student> studentList = new ArrayList<>();
        while (!((buffer = scanner.next()).equals("exit")))
        {
            String[] subBuffer = buffer.split(",");
            Iterator<Student> iterator = studentList.iterator();
            boolean flag = false;
            while(iterator.hasNext())
            {
                Student s = iterator.next();
                if(s.id.equals(subBuffer[1]))
                {
                    s.addGrade(Integer.parseInt(subBuffer[3]));
                    flag = true;
                }
            }
            if(!flag)
            {
                studentList.add(new Student(subBuffer[1],subBuffer[0],
                        Integer.parseInt(subBuffer[3])));
            }
        }
        studentList.sort((s1, s2) ->
        {
            if (s1.getAvg() != s2.getAvg())
            {
                return s2.getAvg() - s1.getAvg();
            }else
            {
                return Integer.parseInt(s1.id) - Integer.parseInt(s2.id);
            }
        });
        Iterator<Student> iterator = studentList.iterator();
        int i = 0;
        while(iterator.hasNext())
        {
            Student s = iterator.next();
            System.out.println("No"+(++i)+":"+s.id+","+s.name);
        }
    }

}

class Student
{
    public String id;
    public String name;
    public int subNum = 0;
    public int subGrade = 0;

    public Student(String id, String name,int subGrade)
    {
        this.id = id;
        this.name = name;
        this.subGrade = subGrade;
        subNum++;
    }

    public void addGrade(int grade)
    {
        subNum++;
        subGrade += grade;
    }

    public int getAvg()
    {
        return subGrade / subNum;
    }

}

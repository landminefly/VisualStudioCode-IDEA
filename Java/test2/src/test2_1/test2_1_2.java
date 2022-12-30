package test2_1;

public class test2_1_2
{
    public static void main(String[] args)
    {
        test2_1_2 test = new test2_1_2();
        test.first();
    }
    public void first()
    {
        int i = 5;
        Value v = new Value();
        v.i = 25;
        second(v,i);
        System.out.println(v.i);//20
        System.out.println(i);//5
    }
    public void second(Value v, int i)
    {
        i = 0;
        v.i = 20;
        Value val = new Value();
        v = val;
        System.out.println(v.i + " " + i); //15 0
    }
}

class Value
{
    int i = 15;
}

// import java.util.Scanner;
//
// public class test2_1_2
// {
//     public static void main(String[] args)
//     {
//         // int[] arr1 = new int[]{1,2,3};
//         // System.out.println(arr1);
//         //
//         // char[] arr2 = new char[]{'a','b','c'};
//         // System.out.println(arr2);
//
//         Scanner scaner = new Scanner(System.in);
//         int i = scaner.nextInt();
//         test2_1_2 test = new test2_1_2();
//         System.out.println("Radius\tArea");
//         test.printAreas(new Circle(),i);
//     }
//     public void printAreas(Circle c, int time)
//     {
//         for(int i = 1; i<=time; i++)
//         {
//             c.radius = i;
//             System.out.println(i+"\t\t"+c.findArea());
//         }
//         System.out.println("now radius is "+(c.radius+1));
//     }
// }
//
// class Circle
// {
//     double radius;
//
//     public double findArea()
//     {
//         return Math.PI*Math.pow(radius,2);
//     }
// }




// public class test2_1_2
// {
//     public static void main(String[] args)
//     {
//         Animal animal = new Animal("frog",-4);
//         System.out.println(animal.name+" "+animal.legs);
//     }
// }
//
// class Animal
// {
//     String name;
//     int legs;
//
//     //构造器
//     public Animal(String n,int l)
//     {
//         name = n;
//         if(l >= 0 && l % 2 == 0)
//         {
//             legs = l;
//         }
//         else
//         {
//             System.out.println("数值不合法，赋值失败");
//         }
//     }

    // public int getLegs()
    // {
    //     return legs;
    // }
    // public void setLegs(int l)
    // {
    //     if(l >= 0 && l % 2 == 0)
    //     {
    //         legs = l;
    //     }
    //     else
    //     {
    //         System.out.println("数值不合法，赋值失败");
    //     }
    // }
// }


package test8_1;

import java.util.Scanner;

abstract class Shape
{
    double pi = 3.1415926;
    abstract double area();
    abstract double perimeter();
}

class Oval extends Shape
{
    private double a;
    private double b;

    public Oval()
    {
        a = 0;
        b = 0;
    }

    public Oval(double a, double b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    double area()
    {
        return pi * a * b;
    }

    @Override
    double perimeter()
    {
        return 2 * Math.PI * b + 4 * (a - b);
    }

    @Override
    public String toString()
    {
        return "Oval(a:"+a+",b:"+b+")";
    }
}

public class Main1
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        var a = scanner.nextDouble();
        var b = scanner.nextDouble();
        var oval = new Oval(a,b);
        System.out.println("The area of "+oval.toString()+" is "+oval.area()+"\n"+
                "The perimeter of "+oval.toString()+" is "+oval.perimeter());
    }
}

package test8_1;

import java.util.Scanner;

public class Homework2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();

        System.out.println(add(s1, s2));

        Integer int1 = null;
        Integer int2 = null;
        try
        {
            int1 = Integer.valueOf(s1);
            int2 = Integer.valueOf(s2);
            System.out.println(add(int1, int2));
        } catch (NumberFormatException e)
        {
            System.out.println("Fail to translate to Integer from String!");
        }

        Double double1 = null;
        Double double2 = null;
        try
        {
            double1 = Double.valueOf(s1);
            double2 = Double.valueOf(s2);
            System.out.println(add(double1, double2));
        } catch (NumberFormatException e)
        {
            System.out.println("Fail to translate to Double from String!");
        }
    }

    public static String add(String s1,String s2)
    {
       return s1.concat(s2);
    }

    public static int add(int num1,int num2)
    {
        return num1 + num2;
    }

    public static double add(double num1,double num2)
    {
        return num1 + num2;
    }
}

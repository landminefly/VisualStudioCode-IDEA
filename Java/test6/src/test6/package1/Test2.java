package test6.package1;

import java.util.Scanner;

public class Test2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        double num1 = scanner.nextInt();
        double num2 = scanner.nextDouble();
        System.out.println(num1 + " + " + num2 + " = " +(num1+num2));
        System.out.println(num1 + " - " + num2 + " = " +(num1-num2));
        System.out.println(num1 + " * " + num2 + " = " +(num1*num2));
        if(num2 == 0)
        {
            System.out.println("The divisor is 0");
        }else
        {
            System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
        }
    }
}

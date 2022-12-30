package test6.package1;

import java.util.Scanner;

public class Test4
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);
        while(a % b != 0)
        {
            int tmp = a % b;
            a = a % b;
            b = tmp;
        }
        System.out.println(b);
    }
}

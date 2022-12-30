package test6.package1;

import java.util.Scanner;

public class Test1
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int temF = scanner.nextInt();
        int temC = 5 * (temF - 32) / 9;
        System.out.println(temC);
    }
}


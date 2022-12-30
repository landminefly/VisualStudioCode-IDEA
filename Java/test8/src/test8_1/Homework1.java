package test8_1;

import java.util.Scanner;

public class Homework1
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        char ch = scanner.next().charAt(0);
        int[] mix = lineCount(num);
        for(int i = 1; i<=mix[0]; i++)
        {
            for(int j = 1; j<=i-1; j++)
                System.out.print(" ");
            for(int j = 1; j<=1+(mix[0]-i)*2; j++)
                System.out.print(ch);
            System.out.println();
        }
        for(int i = 2; i<=mix[0]; i++)
        {
            for(int j = 1; j<=(mix[0]-i); j++)
                System.out.print(" ");
            for(int j = 2; j<=i*2; j++)
                System.out.print(ch);
            System.out.println();
        }
        System.out.println(mix[1]);
    }

    public static int[] lineCount (int num)
    {
        int i = 1;
        int line = 0;
        while(i <= num)
        {
            if(i == 1)
            {
                num -= i;
                i += 5;
                line++;
            }else
            {
                num -= i;
                i += 4;
                line++;
            }
        }
        return new int[]{line,num};
    }
}

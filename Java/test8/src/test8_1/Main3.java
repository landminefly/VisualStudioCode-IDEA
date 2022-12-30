package test8_1;

import java.util.Scanner;

public class Main3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        boolean flag = false;
        for(int i = 1; i<=100; i++)
        {
            int A = (a + i) % 10;
            int B = (b + i) % 10;
            int C = (c + i) % 10;
            if((A % 3 == 0) && (B % 3 == 0) && (C % 3 == 0)
                    && (A != 0) && (B != 0) && (C != 0))
            {
                if((A != B) && (B != C) && (A != C))
                {
                    System.out.println(i);
                    flag = true;
                }
                if(flag)
                    break;
            }
        }
        if(!flag)
            System.out.println("so sad!");
    }
}

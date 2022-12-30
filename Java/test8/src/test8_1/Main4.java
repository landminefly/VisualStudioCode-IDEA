package test8_1;

import java.util.Scanner;

public class Main4
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] triangle = new int[n][n];
        for(int i = 0; i<n; i++)
        {
            for(int j = 0; j<=i; j++)
            {
                if((j == 0)||(j == i))
                {
                    triangle[i][j] = 1;
                }else
                {
                    triangle[i][j] = triangle[i - 1][j] + triangle[i - 1][j - 1];
                }
            }
        }
        for(int i = 0; i<n; i++)
        {
            for(int j = 0; j<=i; j++)
            {
                System.out.printf("%-4d",triangle[i][j]);
            }
            System.out.println();
        }
    }
}

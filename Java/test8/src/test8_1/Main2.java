package test8_1;

import java.util.Arrays;
import java.util.Scanner;

public class Main2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[10];
        for(int i = 0; i<10; i++)
        {
            nums[i] = scanner.nextInt();
        }
        int[] numsCopy = Arrays.copyOf(nums,10);
        Arrays.sort(numsCopy);
        for(int i = 0; i<10; i++)
        {
            if(numsCopy[0] == nums[i])
                System.out.println(i + " ");
        }
    }
}

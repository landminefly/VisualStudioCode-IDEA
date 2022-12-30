package test8_1;

import java.io.*;

public class Homework4
{
    public static void main(String[] args) throws Exception
    {
        int[] nums = new int[]{100, 101, 102, 103, 104, 105};
        FileWriter fileWriter = new FileWriter(new File("Dest.txt"));
        fileWriter.write("{");
        for(int i = 0; i<nums.length; i++)
        {
            if(i != nums.length-1)
                fileWriter.write(Integer.valueOf(nums[i]).toString() + ", ");
            else
                fileWriter.write(Integer.valueOf(nums[i]).toString());
        }
        fileWriter.write("}");
        fileWriter.close();

        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("Dest.txt"), "rw");
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        int[] numsNew = new int[6];
        randomAccessFile.seek(randomAccessFile.length() - 4);
        for(int i = 0; i<6; i++)
        {
            for(int j = 0; j<3; j++)
            {
                ch = randomAccessFile.read();;
                stringBuilder.append((char)ch);
                numsNew[i] = Integer.parseInt(stringBuilder.toString());
            }
            stringBuilder.delete(0,stringBuilder.length());
            if(i != 5)
                randomAccessFile.seek(randomAccessFile.getFilePointer() - 8);
        }
        randomAccessFile.close();

        System.out.print("{");
        for(int i = 0; i<numsNew.length; i++)
        {
            if(i != numsNew.length-1)
                System.out.print(numsNew[i]+",");
            else
                System.out.print(numsNew[i]);
        }
        System.out.print("}");
    }
}

package test8_1;

import java.io.*;

public class Homework3
{
    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(new File("E:/myJava/Hello.java")));
            String buffer = null;
            while((buffer = bufferedReader.readLine()) != null)
            {
                System.out.println(buffer);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

package test8_1;

import java.io.*;

public class APlusB
{
    public static void main(String[] args)
    {
        BufferedReader aBufferedReader = null;
        BufferedReader bBufferedReader = null;
        FileWriter fileWriter = null;
        try
        {
            aBufferedReader = new BufferedReader(new FileReader("a.txt"));
            bBufferedReader = new BufferedReader(new FileReader("b.txt"));
            String s1 = aBufferedReader.readLine();
            String s2 = bBufferedReader.readLine();
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            fileWriter = new FileWriter("c.txt");
            fileWriter.write(String.valueOf(i1+i2));
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                if(aBufferedReader != null)
                    aBufferedReader.close();
                if(bBufferedReader != null)
                    bBufferedReader.close();
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

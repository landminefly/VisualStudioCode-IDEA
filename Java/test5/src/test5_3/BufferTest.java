package test5_3;

import org.junit.jupiter.api.Test;

import java.io.*;

public class BufferTest
{
    @Test
    public void show()
    {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try
        {
            // 1.造文件
            File file1 = new File("Forza Horizon 5 NIO EP9.png");
            File file2 = new File("CopiedImageWithBuffer.png");
            //2.1 造节点流
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            //2.2 造缓冲流
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //3.读取&写入
            byte[] buffer = new byte[1024];
            int length;
            while((length = bufferedInputStream.read(buffer)) != -1)
            {
                bufferedOutputStream.write(buffer,0,length);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //4.关闭流
            if(bufferedInputStream != null)
            {
                try
                {
                    bufferedInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(bufferedOutputStream != null)
            {
                try
                {
                    bufferedOutputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void show2()
    {
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try
        {
            bufferedWriter = new BufferedWriter(new FileWriter(new File("helloNewCopied.txt")));
            bufferedReader = new BufferedReader(new FileReader(new File("helloNew.txt")));
            String buffer;
            //readLine() 方法，一次读取一行的字符（不包括换行符）
            while((buffer = bufferedReader.readLine()) != null)
            {
                bufferedWriter.write(buffer);
                bufferedWriter.newLine();//换行方法
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(bufferedReader != null)
                {
                    bufferedReader.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void show1() throws IOException
    {
        //省略了try-catch-finally的使用，实际应用中还是要记得写
        File file1 = new File("helloWithChinese.txt");
        File file2 = new File("helloWithChineseCopied.txt");

        FileInputStream fileInputStream = new FileInputStream(file1);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");//字符集可不写，下同
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"GBK");

        char[] buffer = new char[10];
        int length;
        while((length = inputStreamReader.read(buffer)) != -1)
        {
            outputStreamWriter.write(buffer,0,length);
        }

        inputStreamReader.close();;
        outputStreamWriter.close();
    }
}

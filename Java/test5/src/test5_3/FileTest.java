package test5_3;

import org.junit.jupiter.api.Test;

import java.io.*;


public class FileTest
{
    // @Test
    // public void test1()
    // {
    //     File file1 = new File("d:"+File.separator+"Hello"+File.separator+"hello.txt");
    //     File file2 = new File("d:\\Hello\\hello.txt");//������Windows�µȼ�
    // }

    @Test
    public void test2()
    {
        FileReader fileReader = null;
        try
        {
            //1.ʵ����File��ָ��Ҫ�������ļ�
            File file = new File("hello.txt");
            //2.ʵ�����������
            fileReader = new FileReader(file);
            //3.���ݵĶ���
            char[] cbuf = new char[5];
            int len;
            while((len = fileReader.read(cbuf)) != -1)
            {
                for(int i = 0; i<len; i++)
                    System.out.print(cbuf[i]);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //4.�ر���
            //5.ʹ��try-catch-finally����4֮ǰ�Ĳ���
            //6.��ʹ��try-catch�������Ĺرղ���
            try
            {
                //if���Է���try-catch��ߣ�Ҳ���Է������
                if(fileReader != null)
                    fileReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3()
    {
        FileWriter fileWriter = null;
        try
        {
            File file = new File("hello1.txt");
            fileWriter = new FileWriter(file);
            fileWriter.write("hello world!");
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(fileWriter != null)
                fileWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test4()
    {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            File src = new File("Forza Horizon 5 NIO EP9.png");
            File dest = new File("CopiedImage.png");
            fileInputStream = new FileInputStream(src);
            fileOutputStream = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int len;
            while((len = fileInputStream.read(buffer)) != -1)
            {
                fileOutputStream.write(buffer,0,len);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            //���ڶ�����Ĺرղ���������ʹ��try-catch-finallyǶ�ף�Ҳ�������²���
            try
            {
                if(fileInputStream != null)
                fileInputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try
            {
                if(fileOutputStream != null)
                fileOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

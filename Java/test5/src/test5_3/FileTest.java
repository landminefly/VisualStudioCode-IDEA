package test5_3;

import org.junit.jupiter.api.Test;

import java.io.*;


public class FileTest
{
    // @Test
    // public void test1()
    // {
    //     File file1 = new File("d:"+File.separator+"Hello"+File.separator+"hello.txt");
    //     File file2 = new File("d:\\Hello\\hello.txt");//二者在Windows下等价
    // }

    @Test
    public void test2()
    {
        FileReader fileReader = null;
        try
        {
            //1.实例化File，指明要操作的文件
            File file = new File("hello.txt");
            //2.实例化具体的流
            fileReader = new FileReader(file);
            //3.数据的读入
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
            //4.关闭流
            //5.使用try-catch-finally包起4之前的步骤
            //6.再使用try-catch包起流的关闭操作
            try
            {
                //if可以放在try-catch外边，也可以放在里边
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
            //对于多个流的关闭操作，可以使用try-catch-finally嵌套，也可以如下并列
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

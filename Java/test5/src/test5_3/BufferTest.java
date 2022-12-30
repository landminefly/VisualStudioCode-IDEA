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
            // 1.���ļ�
            File file1 = new File("Forza Horizon 5 NIO EP9.png");
            File file2 = new File("CopiedImageWithBuffer.png");
            //2.1 ��ڵ���
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            //2.2 �컺����
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //3.��ȡ&д��
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
            //4.�ر���
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
            //readLine() ������һ�ζ�ȡһ�е��ַ������������з���
            while((buffer = bufferedReader.readLine()) != null)
            {
                bufferedWriter.write(buffer);
                bufferedWriter.newLine();//���з���
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
        //ʡ����try-catch-finally��ʹ�ã�ʵ��Ӧ���л���Ҫ�ǵ�д
        File file1 = new File("helloWithChinese.txt");
        File file2 = new File("helloWithChineseCopied.txt");

        FileInputStream fileInputStream = new FileInputStream(file1);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");//�ַ����ɲ�д����ͬ
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

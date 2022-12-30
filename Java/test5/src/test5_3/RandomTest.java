package test5_3;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomTest
{
    @Test
    public void test() throws IOException
    {
        //ʡ����try-catch-finally�ṹ
        //ʵ�ַ��ı��ļ��ļ�����
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(new File("Forza Horizon 5 NIO EP9.png"), "r");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File("CopiedImageWithRandom.png"), "rw");
        byte[] buffer = new byte[1024];
        int length;
        while((length = randomAccessFile1.read(buffer)) != -1)
        {
            randomAccessFile2.write(buffer,0,length);
        }
        randomAccessFile1.close();
        randomAccessFile2.close();
    }

    @Test
    public void test2() throws IOException
    {
        //ʡ����try-catch-finally�ṹ
        //ʵ���ļ��ض�λ�õ����ݸ���
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("ABC.txt"),"rw");
        randomAccessFile.seek(3);
        randomAccessFile.write("AVC".getBytes());
        //or
        //randomAccessFile.writeBytes("AVC");
        randomAccessFile.close();
    }

    @Test
    public void test3() throws IOException
    {
        //ʡ����try-catch-finally�ṹ
        //ʵ���ļ��ض�λ�õ����ݲ���
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("ABC.txt"),"rw");
        //����ԭ�ļ�Ҫ����λ��֮�������
        StringBuilder stringBuilder = new StringBuilder((int) new File("ABC.txt").length());
        randomAccessFile.seek(3);
        String buffer;
        while((buffer = randomAccessFile.readLine()) != null)
        {
            stringBuilder.append(buffer);
        }
        //���ض�λ�ý������ݸ���
        randomAccessFile.seek(3);
        randomAccessFile.write("AVC".getBytes());
        //����֮����д��֮ǰ���������
        randomAccessFile.write(stringBuilder.toString().getBytes());
        //or
        //randomAccessFile.writeBytes(stringBuilder.toString());
        randomAccessFile.close();
    }
}

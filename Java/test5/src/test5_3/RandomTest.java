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
        //省略了try-catch-finally结构
        //实现非文本文件文件复制
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
        //省略了try-catch-finally结构
        //实现文件特定位置的内容覆盖
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
        //省略了try-catch-finally结构
        //实现文件特定位置的内容插入
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("ABC.txt"),"rw");
        //保存原文件要插入位置之后的内容
        StringBuilder stringBuilder = new StringBuilder((int) new File("ABC.txt").length());
        randomAccessFile.seek(3);
        String buffer;
        while((buffer = randomAccessFile.readLine()) != null)
        {
            stringBuilder.append(buffer);
        }
        //对特定位置进行内容覆盖
        randomAccessFile.seek(3);
        randomAccessFile.write("AVC".getBytes());
        //覆盖之后再写上之前保存的内容
        randomAccessFile.write(stringBuilder.toString().getBytes());
        //or
        //randomAccessFile.writeBytes(stringBuilder.toString());
        randomAccessFile.close();
    }
}

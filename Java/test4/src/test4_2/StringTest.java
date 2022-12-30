package test4_2;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class StringTest
{
    @Test
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        // String s1 = "abc";
        // String s2 = new String("abc");
        // System.out.println(s1 == s2);//false
        // System.out.println(s1.equals(s2));//true
        // System.out.println(s2.intern() == s1);//true
        //
        // String s = "123";
        // Integer in = Integer.valueOf(s);
        // // System.out.println(in);
        //
        // String s1 = "Ava向晚";
        // char[] c = s1.toCharArray();//String->char[]
        // String s2 = new String(c);//char[]->String

        // String s1 = "Ava向晚";
        // byte[] b1 = s1.getBytes();//编码：String->byte[]
        // String s2 = new String(b1);//解码：byte[]->String
        //
        // String s3 = "Diana嘉然";
        // byte[] b2 = s3.getBytes("UTF-8");//指定编码集
        // String s4 = new String(b2,"UTF-8");//指定解码集

        StringBuilder s = new StringBuilder();
        s.append("嘉然").append("向晚").append("贝拉");//方法链
    }
}


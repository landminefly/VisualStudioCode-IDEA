package test4_2;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTest
{
    @Test
    public void Date()
    {
        Date d1 = new Date();
        System.out.println(d1.toString());
        System.out.println(d1.getTime());

        java.sql.Date t2 = new java.sql.Date(1234567890L);//java.sql.Date

        Date t3 = new java.sql.Date(1234567890L);//java.sql.Date->java.util.Date

        Date t4 = new Date();
        java.sql.Date t5 = new java.sql.Date(t4.getTime());//java.util.Date->java.sql.Date
    }

    @Test
    public void Date2() throws ParseException
    {
        //��ʽ��������->�ַ���
        // Ĭ�ϸ�ʽ
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        Date d1 = new Date();
        String s1 = sdf1.format(d1);

        //�Զ����ʽ
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
        //MΪ�£�mΪ�֣�HHΪ24Сʱ�ƣ�hhΪ12Сʱ��
        Date d2 = new Date();
        String s2 = sdf2.format(d2);

        //�������ַ���->����(Ҫ���쳣)
        Date d3 = sdf1.parse(s1);
        Date d4 = sdf2.parse(s2);
    }

    @Test
    public String StringTest(String s1,String s2)
    {
        String longS = (s1.length() >= s2.length()) ? s1 : s2;
        String shortS = (s1.length() > s2.length()) ? s2 : s1;
        int longL = longS.length();
        int shortL = shortS.length();
        for(int rightFlag = shortL; rightFlag>0; rightFlag--)
        {
            int left = 0;
            int right = rightFlag;
            while(right <= shortL)
            {
                String subSting = shortS.substring(left, right);
                int index = longS.indexOf(subSting);
                if(index != -1)
                {
                    return subSting;
                }
                right++;
                left++;
            }
        }
        return null;
    }

    public static void main(String[] args)
    {
        DataTest d = new DataTest();
        System.out.println(d.StringTest("abcwerhelloyuiodefabcdef","cvhellobnm"));
    }
}

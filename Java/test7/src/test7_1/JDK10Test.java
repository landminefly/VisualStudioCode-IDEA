package test7_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JDK10Test
{
    @Test
    public void test1()
    {
        var list = new ArrayList<Integer>();
        list.add(12);
        list.add(23);
        for(var i : list)
        {
            System.out.println(i);
        }

        //û�г�ʼ�����ʼֵΪnull
        // var a1;
        // var a2 = null;

        //Lambda���ʽ
        Supplier<Double> b1 = () -> Math.random();
        // var b2 = () -> Math.random();

        //��������
        Consumer<String> c1 = System.out::println;
        // var c2 = System.out::println;

        //���龲̬��ʼ��
        int[] d1 = {12,23,34};
        // var d2 = {12,23,34};//��
        var d3 = new int[]{12,23,34};//��

        var list1 = List.of(12,23,34);
        //����ֻ������
        var list2 = List.copyOf(list1);
        System.out.println(list1 == list2);//true

        var list3 = new ArrayList<>();
        //����ֻ������
        var list4 = List.copyOf(list3);
        System.out.println(list3 == list4);//false
    }

    @Test
    public void test2()
    {

    }
}

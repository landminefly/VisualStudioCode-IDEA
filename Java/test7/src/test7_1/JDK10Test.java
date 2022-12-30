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

        //没有初始化或初始值为null
        // var a1;
        // var a2 = null;

        //Lambda表达式
        Supplier<Double> b1 = () -> Math.random();
        // var b2 = () -> Math.random();

        //方法引用
        Consumer<String> c1 = System.out::println;
        // var c2 = System.out::println;

        //数组静态初始化
        int[] d1 = {12,23,34};
        // var d2 = {12,23,34};//×
        var d3 = new int[]{12,23,34};//√

        var list1 = List.of(12,23,34);
        //创建只读集合
        var list2 = List.copyOf(list1);
        System.out.println(list1 == list2);//true

        var list3 = new ArrayList<>();
        //创建只读集合
        var list4 = List.copyOf(list3);
        System.out.println(list3 == list4);//false
    }

    @Test
    public void test2()
    {

    }
}

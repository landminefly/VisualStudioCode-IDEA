package test5_5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReferenceTest
{
    @Test
    public void test1()
    {
        //对象 :: 实例方法
        String s1 = "Nanjing";
        //       形参   返回值
        Function<String,Boolean> isEqual = s1 :: equals;
        System.out.println(isEqual.apply("Nanjing"));
        System.out.println(isEqual.apply("Beijing"));


        //accept()和println()的返回值都是void
        //      形参
        Consumer<String> print = System.out::println;
        print.accept(s1);
    }

    @Test
    public void test2()
    {
        //类 :: 静态方法
        //         形参1   形参2    返回值
        BiFunction<Integer,Integer,Integer> isEqual = Integer :: compare;
        System.out.println(isEqual.apply(12, 12));
        System.out.println(isEqual.apply(13, 12));
        System.out.println(isEqual.apply(11, 12));
    }

    @Test
    public void test3()
    {
        //类 :: 实例方法
        //调用该方法的对象  形参   返回值
        BiFunction<String,String,Boolean> isEqual = String :: equals;
        System.out.println(isEqual.apply("Analyse", "Analyse"));
        System.out.println(isEqual.apply("Analyse", "Analysis"));

        //调用该方法的对象 返回值
        Function<Person,String> getName = Person :: getName;
        String s1 = getName.apply(new Person("President Xi", 2106010220));
        System.out.println(s1);
    }

    @Test
    public void test4()
    {
        //         name   id      返回的对象
        BiFunction<String,Integer,Person> newPerson1 = Person :: new;
        Person p1 = newPerson1.apply("President Xi", 2106010220);
        //       返回的对象（空参构造器）
        Supplier<Person> newPerson2 = Person::new;
        Person p2 = newPerson2.get();
    }

    @Test
    public void test5()
    {
        //       数组长度 数组类型
        Function<Integer,String[]> newArray = String[] :: new;
        String[] apply = newArray.apply(5);
        System.out.println(Arrays.toString(apply));
    }
}

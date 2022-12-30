package test5_5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaTest
{
    @Test
    public void test1()
    {
        Runnable runnable = () -> System.out.println("I love President Xi");
        runnable.run();
    }

    @Test
    public void test2()
    {
        List<String> list = Arrays.asList("北京","南京","西京","天津");
        ArrayList<String> jing = listContainsJing(list, s -> s.contains("京"));
        System.out.println(jing);
    }

    public ArrayList<String> listContainsJing(List<String> list, Predicate<String> predicate)
    {
        ArrayList<String> arrayList = new ArrayList<>();

        for(String s : list)
        {
            if(predicate.test(s))
                arrayList.add(s);
        }
        return arrayList;
    }

    @Test
    public void test3()
    {
        method(()-> System.out.println("Yes."));
    }

    public void method(Runnable runnable)
    {
        runnable.run();
    }
}

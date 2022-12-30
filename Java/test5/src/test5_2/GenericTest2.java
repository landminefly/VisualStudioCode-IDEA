package test5_2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GenericTest2
{
    @Test
    public void test()
    {
        ArrayList<Object> list1 = null;
        ArrayList<?> list2 = new ArrayList<>();

        list2 = list2;//±àÒëÍ¨¹ı

        list2.add(null);
        Object o = list2.get(0);
    }
}

package test5_1;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ListTest
{
    @Test
    public void test()
    {
        // HashMap hashMap = new HashMap();
        // hashMap.put(1,"A");
        // hashMap.put(2,"B");
        // hashMap.put(3,"C");
        // Set set = hashMap.entrySet();
        // System.out.println(set);

        List src = new ArrayList();
        src.add("AA");
        src.add("BB");
        src.add("CC");

        List dest = Arrays.asList(new Object[src.size()]);
        Collections.copy(dest,src);

        System.out.println(dest);
    }
}

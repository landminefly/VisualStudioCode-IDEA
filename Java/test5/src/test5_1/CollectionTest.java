package test5_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTest
{
    @Test
    public void Test1()
    {
        Collection collection = new ArrayList();
        collection.add("AA");
        collection.add("BB");
        collection.add("CC");
        double[] doubles = new double[]{1.0,2.0,3.0};

        //for(Ҫ������Ԫ������ �Զ���Ԫ���� : Ҫ�����Ľṹ����)
        for(Object o : collection)//����
        {
            System.out.println(o);
        }
        for(double d : doubles)//����
        {
            System.out.println(d);
        }

        for(double d : doubles)
        {
            d = 1.0;
        }
        for(double d : doubles)
        {
            System.out.println(d);
        }
    }
}

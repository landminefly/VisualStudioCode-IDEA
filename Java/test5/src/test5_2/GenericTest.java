package test5_2;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GenericTest
{
    @Test
    public void Test()
    {
        // //标准写法
        // //ArrayList<Person> arrayList = new ArrayList<Person>();
        //
        // //简略写法
        // ArrayList<Person> arrayList = new ArrayList<>();
        // arrayList.add(new Person("A",1));
        // arrayList.add(new Person("B",2));
        // arrayList.add(new Person("C",3));
        //
        // //遍历1
        // Iterator<Person> iterator = arrayList.iterator();//迭代器也有泛型
        // while(iterator.hasNext())
        // {
        //     Person p = iterator.next();//不用强转
        //     System.out.println(p);
        // }
        //
        // //遍历2
        // for(Person p : arrayList)
        // {
        //     System.out.println(p);
        // }

        // HashMap<Integer,Person> hashMap = new HashMap<>();
        // hashMap.put(1,new Person("D",4));
        // hashMap.put(2,new Person("E",5));
        // hashMap.put(3,new Person("F",6));
        //
        // //泛型的嵌套
        // Set<Map.Entry<Integer, Person>> entries = hashMap.entrySet();
        // Iterator<Map.Entry<Integer, Person>> iterator = entries.iterator();
        //
        // while(iterator.hasNext())
        // {
        //     System.out.println(iterator.next());
        // }

        // Object[] objects = null;
        // String[] strings = new String[3];
        // objects = strings;
        // objects[0] = new Integer(12);//报错：ArrayStoreException

        // ArrayList<Object> arrayList1 = null;
        // ArrayList<String> arrayList2 = null;
        // arrayList1 = arrayList2;//×
    }
}


package test9_1;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReviewTest_3
{
    public static void main(String[] args)
    {
        NewThread newThread = new NewThread();
        Thread thread1 = new Thread(newThread);
        thread1.setName("线程一");
        Thread thread2 = new Thread(newThread);
        thread2.setName("线程二");
        Thread thread3 = new Thread(newThread);
        thread3.setName("线程三");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    @Test
    public void test1()
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.withDayOfYear(100);
        System.out.println(dateTimeFormatter.format(localDateTime1));

        BigInteger bigInteger = new BigInteger("348928934627894981264789");
        BigInteger pow = bigInteger.pow(12);
        System.out.println(pow);
    }
}

class NewThread implements Runnable
{
    int i = 0;
    @Override
    public void run()
    {
        while(true)
        {
            synchronized (NewThread.class)
            {
                if(i < 50000)
                {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                }else
                {
                    break;
                }
            }
        }
    }
}

enum GuangDongProvince
{
    GuangZhou(1,"广州"),
    ShenZhen(2,"深圳"),
    QingYuan(3,"清远");

    private final int id;
    private final String CNName;

    GuangDongProvince(int id, String CNName)
    {
        this.id = id;
        this.CNName = CNName;
    }
}

class ReviewTest_3_1
{
    @Test
    public void test1()
    {
        System.out.println(GuangDongProvince.QingYuan);
        for (GuangDongProvince value : GuangDongProvince.values())
        {
            System.out.println(value);
        }
    }

    @Test
    public void test2()
    {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(2);
        hashSet.add(1);
        for (Integer integer : hashSet)
        {
            System.out.println(integer);
        }
    }

    @Test
    public void test3()
    {
        HashMap<Integer,GuangDongProvince> hashMap = new HashMap<>();
        hashMap.put(1,GuangDongProvince.GuangZhou);
        hashMap.put(2,GuangDongProvince.ShenZhen);
        hashMap.put(3,GuangDongProvince.QingYuan);
        Set<Map.Entry<Integer, GuangDongProvince>> entries = hashMap.entrySet();
        Iterator<Map.Entry<Integer, GuangDongProvince>> iterator = entries.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
        Collection<GuangDongProvince> values = hashMap.values();
        Iterator<GuangDongProvince> iterator1 = values.iterator();
        while(iterator1.hasNext())
        {
            System.out.println(iterator1.next());
        }
    }
}

class People1<E>
{
    int age;
    String name;
    E e;

    public People1(int age, String name,E e)
    {
        this.age = age;
        this.name = name;
        this.e = e;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People1 people = (People1) o;

        if (age != people.age) return false;
        return name.equals(people.name);
    }

    @Override
    public int hashCode()
    {
        int result = age;
        result = 31 * result + name.hashCode();
        return result;
    }

    public <T> T generic(T t)
    {
        return t;
    }
}
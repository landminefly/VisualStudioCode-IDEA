package test7_1;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

interface A
{
    private void show()
    {
        System.out.println("A");
    }

    default void method()
    {
        show();
    }
}
public class JDK9Test
{
    @Test
    public void test1() throws FileNotFoundException
    {

        Comparable<Person> c = new Comparable<Person>()
        {
            @Override
            public int compareTo(Person o)
            {
                return 0;
            }
        };

        //JDK8写法
        try(FileReader reader = new FileReader(new File("hello.txt")))
        {
            char[] buffer = new char[10];
            int len;
            while((len = reader.read(buffer)) != -1)
            {
                for(int i = 0; i<len; i++)
                {
                    System.out.print(buffer[i]);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println();

        //JDK9写法
        FileReader reader = new FileReader(new File("hello.txt"));
        try(reader)
        {
            char[] buffer = new char[10];
            int len;
            while((len = reader.read(buffer)) != -1)
            {
                for(int i = 0; i<len; i++)
                {
                    System.out.print(buffer[i]);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test2()
    {
        //创建只读集合
        //方式一：
        List<Integer> numList1 = new ArrayList<>();
        numList1.add(12);
        numList1.add(23);
        numList1.add(34);
        numList1 = Collections.unmodifiableList(numList1);

        //方式二：（只有List能用）
        List<Integer> numList2 = Arrays.asList(12,23,34);

        //方式三：of() -- JDK9新增
        List<Integer> numList3 = List.of(12, 23, 34);

        Set<Integer> numSet = Set.of(12,23,34);

        Map<String,Integer> numMap1 = Map.of("A",1,"B",2,"c",3);
        Map<String,Integer> numMap2 = Map.ofEntries(Map.entry("A",1),
                                                    Map.entry("B",2),
                                                    Map.entry("C",3));
    }

    @Test
    public void test3() throws Exception
    {
        //复制文件
        try(InputStream inputStream = new FileInputStream(new File("hello.txt"));
            OutputStream outputStream = new FileOutputStream(new File("helloCopied.txt")))
        {
            inputStream.transferTo(outputStream);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test4()
    {
        List<Integer> numList = List.of(26,43,23,75,67,45,98,32);
        numList.stream().takeWhile(x -> x < 50).forEach(System.out::println);
        //26 43 23
        numList.stream().dropWhile(x -> x < 50).forEach(System.out::println);
        //75 67 45 98 32

        Stream.of(12,23,null);//√
        // Stream.of(null);//×,NullPointerException
        Stream.of(null,null);//√
        Stream.ofNullable(null);//√

        //旧控制方式
        Stream.iterate(1, i -> i+1).limit(10).forEach(System.out::println);
        //JDK9新控制方式
        Stream.iterate(1, i -> i<=10, i -> i+1).forEach(System.out::println);

        List<String> alphaList = Arrays.asList("A","B","C");
        Optional<List<String>> optional = Optional.ofNullable(alphaList);
        //list整体被当成一个元素，所以此时stream只有一个元素
        Stream<List<String>> stream = optional.stream();
        //先将list转换成stream，再用flagMap将其中的String取出来
        stream.flatMap(x -> x.stream()).forEach(System.out::println);
    }
}

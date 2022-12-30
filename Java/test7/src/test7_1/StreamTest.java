package test7_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest
{
    @Test
    public void test1()
    {
        //一、通过集合
        List<Person> personList = new ArrayList<>();
        //返回一个顺序流
        Stream<Person> personstream1 = personList.stream();
        //返回一个并行流
        Stream<Person> personStream2 = personList.parallelStream();

        //二、通过数组
        int[] ints = new int[]{1,2,3,4,5};
        IntStream intsStream = Arrays.stream(ints);

        Person[] people = new Person[]{new Person("Diana", 17), new Person("Ava", 18)};
        Stream<Person> stream = Arrays.stream(people);

        //三、通过Stream的of()
        Stream<Person> personStream = Stream.of(new Person("Diana", 17), new Person("Ava", 18));
    }

    @Test
    public void test2()
    {
        ArrayList<Person> Asoul = new ArrayList<>();
        Asoul.add(new Person("Diana",17));
        Asoul.add(new Person("Ava",18));
        Asoul.add(new Person("Bella",19));
        Asoul.add(new Person("Carol",20));
        Asoul.add(new Person("Eileen",21));

        Asoul.stream().filter(p -> p.getName().length() > 5).forEach(p -> System.out.println(p.getName()));
        System.out.println();

        Asoul.stream().limit(3).forEach(System.out::println);
        System.out.println();

        Asoul.stream().skip(3).forEach(System.out::println);
        System.out.println();

        Asoul.add(new Person("Diana",17));
        Asoul.add(new Person("Diana",17));
        Asoul.add(new Person("Diana",17));
        Asoul.stream().distinct().forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void test3()
    {
        ArrayList<Person> Asoul = new ArrayList<>();
        Asoul.add(new Person("Diana",17));
        Asoul.add(new Person("Ava",18));
        Asoul.add(new Person("Bella",19));
        Asoul.add(new Person("Carol",20));
        Asoul.add(new Person("Eileen",21));

        //将集合中元素的name属性全部改成大写
        Asoul.stream().map(person ->
        {
            person.setName(person.getName().toUpperCase());
            return person;
        }).forEach(System.out::println);
        System.out.println();

        //flapMap()将形参返回的stream做了拆解，拿出了其中的Character并合成一个新的stream
        Stream<Character> characterStream1 = Asoul.stream().flatMap(person ->
        {
            char[] name1 = person.getName().toCharArray();
            ArrayList<Character> characters1 = new ArrayList<>();
            for (Character c : name1)
            {
                characters1.add(c);
            }
            //返回一个steam
            return characters1.stream();
        });
        characterStream1.forEach(System.out::print);
        System.out.println();

        //map()将形参返回的stream当作一整个元素去处理，返回一个stream里套stream的对象
        Stream<Stream<Character>> characterStream2 = Asoul.stream().map(person ->
        {
            char[] name1 = person.getName().toCharArray();
            ArrayList<Character> characters1 = new ArrayList<>();
            for (Character c : name1)
            {
                characters1.add(c);
            }
            //返回一个steam
            return characters1.stream();
        });
        //遍历是要嵌套forEach()
        characterStream2.forEach(s ->
        {
            s.forEach(System.out::print);
        });
        System.out.println();
    }

    @Test
    public void test5()
    {
        //自然排序
        List<Integer> numList = Arrays.asList(45,12,39,-12,88);
        numList.stream().sorted().forEach(System.out::println);
        System.out.println();

        //定制排序：传入Comparator的实例
        ArrayList<Person> Asoul = new ArrayList<>();
        Asoul.add(new Person("Diana",17));
        Asoul.add(new Person("Ava",18));
        Asoul.add(new Person("Bella",19));
        Asoul.add(new Person("Carol",20));
        Asoul.add(new Person("Eileen",21));
        Asoul.stream().sorted((p1,p2) -> -(p1.getAge() - p2.getAge())).forEach(System.out::println);
    }

    @Test
    public void test6()
    {
        ArrayList<Person> Asoul = new ArrayList<>();
        Asoul.add(new Person("Diana",17));
        Asoul.add(new Person("Ava",18));
        Asoul.add(new Person("Bella",19));
        Asoul.add(new Person("Carol",20));
        Asoul.add(new Person("Eileen",21));

        boolean isUnder25 = Asoul.stream().allMatch(p -> p.getAge() < 25);
        System.out.println(isUnder25);

        //在筛选出 age < 20 的元素后判断stream里是否没有 age < 20 的元素
        boolean b = Asoul.stream().filter(p -> p.getAge() < 20).noneMatch(p -> p.getAge() < 20);
        System.out.println(b);

        Optional<Person> min = Asoul.stream().min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        System.out.println(min);

        //求出age之和
        Optional<Integer> reduce1 = Asoul.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println(reduce1);

        //带初始值的reduce()                                         初始值
        Integer reduce2 = Asoul.stream().map(Person::getAge).reduce(10, Integer::sum);
        System.out.println(reduce2);

        //reduce()的参数为函数式接口BinaryOperator()的实例
        //注意不要这样写，因为BinaryOperator()要求其返回值和形参的数据类型要相等
        // Asoul.stream().reduce((p1,p2)->Integer.sum(p1.getAge(),p2.getAge()));

        List<Person> collect = Asoul.stream().filter(p -> p.getAge() < 20).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test7()
    {
        Person p1 = new Person();
        Optional<String> nameOptional = Optional.ofNullable(p1.getName());
        String name = nameOptional.orElse("PersidentXi");
        System.out.println(name);//PresidentXi
    }
}

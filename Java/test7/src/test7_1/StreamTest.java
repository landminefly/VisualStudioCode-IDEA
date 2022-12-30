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
        //һ��ͨ������
        List<Person> personList = new ArrayList<>();
        //����һ��˳����
        Stream<Person> personstream1 = personList.stream();
        //����һ��������
        Stream<Person> personStream2 = personList.parallelStream();

        //����ͨ������
        int[] ints = new int[]{1,2,3,4,5};
        IntStream intsStream = Arrays.stream(ints);

        Person[] people = new Person[]{new Person("Diana", 17), new Person("Ava", 18)};
        Stream<Person> stream = Arrays.stream(people);

        //����ͨ��Stream��of()
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

        //��������Ԫ�ص�name����ȫ���ĳɴ�д
        Asoul.stream().map(person ->
        {
            person.setName(person.getName().toUpperCase());
            return person;
        }).forEach(System.out::println);
        System.out.println();

        //flapMap()���βη��ص�stream���˲�⣬�ó������е�Character���ϳ�һ���µ�stream
        Stream<Character> characterStream1 = Asoul.stream().flatMap(person ->
        {
            char[] name1 = person.getName().toCharArray();
            ArrayList<Character> characters1 = new ArrayList<>();
            for (Character c : name1)
            {
                characters1.add(c);
            }
            //����һ��steam
            return characters1.stream();
        });
        characterStream1.forEach(System.out::print);
        System.out.println();

        //map()���βη��ص�stream����һ����Ԫ��ȥ��������һ��stream����stream�Ķ���
        Stream<Stream<Character>> characterStream2 = Asoul.stream().map(person ->
        {
            char[] name1 = person.getName().toCharArray();
            ArrayList<Character> characters1 = new ArrayList<>();
            for (Character c : name1)
            {
                characters1.add(c);
            }
            //����һ��steam
            return characters1.stream();
        });
        //������ҪǶ��forEach()
        characterStream2.forEach(s ->
        {
            s.forEach(System.out::print);
        });
        System.out.println();
    }

    @Test
    public void test5()
    {
        //��Ȼ����
        List<Integer> numList = Arrays.asList(45,12,39,-12,88);
        numList.stream().sorted().forEach(System.out::println);
        System.out.println();

        //�������򣺴���Comparator��ʵ��
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

        //��ɸѡ�� age < 20 ��Ԫ�غ��ж�stream���Ƿ�û�� age < 20 ��Ԫ��
        boolean b = Asoul.stream().filter(p -> p.getAge() < 20).noneMatch(p -> p.getAge() < 20);
        System.out.println(b);

        Optional<Person> min = Asoul.stream().min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        System.out.println(min);

        //���age֮��
        Optional<Integer> reduce1 = Asoul.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println(reduce1);

        //����ʼֵ��reduce()                                         ��ʼֵ
        Integer reduce2 = Asoul.stream().map(Person::getAge).reduce(10, Integer::sum);
        System.out.println(reduce2);

        //reduce()�Ĳ���Ϊ����ʽ�ӿ�BinaryOperator()��ʵ��
        //ע�ⲻҪ����д����ΪBinaryOperator()Ҫ���䷵��ֵ���βε���������Ҫ���
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

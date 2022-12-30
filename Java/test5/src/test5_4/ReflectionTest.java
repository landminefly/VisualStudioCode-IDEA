package test5_4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Properties;

public class ReflectionTest
{
    @Test
    public void test1() throws ClassNotFoundException
    {
        //��ʽһ����������ʱ������ԣ�.class
        Class c1 = Person.class;

        //��ʽ����ͨ������ʱ��Ķ���,����getClass()
        Person p1 = new Person();
        Class c2 = p1.getClass();

        //��ʽ��������Class�ľ�̬������forName(String classPath)
        Class c3 = Class.forName("test5_4.Person");//дĿǰ�����ڵ���Ҳ���ᱨ��

        //��ʽ�ģ�ʹ����ļ�������ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class c4 = classLoader.loadClass("test5_4.Person");//ͬ��

        //����ʱ�����ڴ���ֻ�����һ�ݣ���˻�ȡ��ͬһ�����Classʵ����ָ��ͬһ���ڴ�ռ�
        System.out.println(c1 == c2 && c2 == c3 && c3 == c4);//true
    }

    @Test
    public void test2() throws IOException
    {
        //�����Զ����࣬ʹ��ϵͳ����������м���
        ClassLoader classLoader1 = ReflectionTest.class.getClassLoader();
        System.out.println(classLoader1);
        //����ϵͳ���������getParent()����ȡ��չ�������
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
        //������չ���������getParent()���޷���ȡ�����������
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);//null

        //��ȡ�����ļ��ķ�ʽ��ʹ��ClassLoader
        ClassLoader classLoader4 = ReflectionTest.class.getClassLoader();
        //�����ļ�Ĭ��λ��Ϊ����ǰmodule��src��
        InputStream inputStream = classLoader4.getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String name = properties.getProperty("name");
        System.out.println(name);
    }

    @Test
    public void test3() throws Exception
    {
        Class<Person> personClass = Person.class;
        //������Ķ���

        //��ʽһ������ָ��������������Constructor��ʵ�����ø�ʵ��������Ķ��󣬲���ʼ������
        Constructor<Person> personConstructor = personClass.getConstructor(String.class,int.class);
        Person person1 = personConstructor.newInstance("ϰ��ƽ",67);

        //��ʽ����ֱ��ʹ��Classʵ����������Ҫ���������пղι����������÷�����JDK9�͹�ʱ�ˣ�
        Person person2 = personClass.newInstance();
    }

    @Test
    public void fieldTest1()
    {
        Class<Person> personClass = Person.class;
        //��ȡ�������
        //1.��ȡ���༰�丸��������Ϊpublic����Ȩ�޵�����
        Field[] fields = personClass.getFields();
        for(Field f : fields)
        {
            System.out.println(f);
        }
        System.out.println();

        //2.��ȡ���ࣨ���������ࣩ���������������ԡ�������private��
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for(Method m : declaredMethods)
        {
            System.out.println(m);
        }
    }

    @Test
    public void fieldTest2()
    {
        Class<Person> personClass = Person.class;
        Field[] declaredFields = personClass.getDeclaredFields();
        //��ȡ���ԵĽṹ
        for(Field f : declaredFields)
        {
            //1.Ȩ�����η�
            //���ص�intֵ��Ӧ��һ��Ȩ�����η�������toString��̬������ԭ�ɶ�Ӧ���ַ���
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + " ");

            //2.��������
            Class type = f.getType();
            System.out.print(type + " ");

            //3.������
            String name = f.getName();
            System.out.print(name + " ");

            System.out.println();
        }
    }

    @Test
    public void methodTest1()
    {
        Class<Person> personClass = Person.class;

        //��ȡ��ǰ����ʱ�༰�����и�����Ȩ��Ϊpublic�ķ���
        Method[] methods = personClass.getMethods();
        for(Method m : methods)
        {
            System.out.println(m);
        }
        System.out.println();

        //��ȡ��ǰ����ʱ�ࣨ���������ࣩ�����������з�����������private��
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for(Method m : declaredMethods)
        {
            System.out.println(m);
        }
    }

    @Test
    public void methodTest2()
    {
        Class<Person> personClass = Person.class;
        Method[] methods = personClass.getDeclaredMethods();

        //��ȡ�����Ľṹ
        for(Method m : methods)
        {
            //��ȡ����������ע��
            Annotation[] annotations = m.getAnnotations();
            for(Annotation a : annotations)
            {
                System.out.println(a);
            }

            //��ȡ������Ȩ�����η�
            System.out.print(Modifier.toString(m.getModifiers()) + " ");

            //��ȡ�����ķ���ֵ����
            System.out.print(m.getReturnType() + " ");

            //��ȡ�����ķ�����
            System.out.print(m.getName() + " ");

            //��ȡ�����������β�
            System.out.print("(");
            Class<?>[] parameterTypes = m.getParameterTypes();
            for(int i = 0; i<parameterTypes.length; i++)
            {
                if(i == parameterTypes.length - 1)
                    System.out.print(parameterTypes[i].getName() + " args_" + (i+1));
                else
                    System.out.print(parameterTypes[i].getName() + " args_" + (i+1) + ",");
            }
            System.out.print(")");

            //��ȡ�����׳��ĵ������쳣
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if(exceptionTypes.length != 0)
            {
                System.out.print("throws ");
                for(int i = 0; i<exceptionTypes.length; i++)
                {
                    if(i == exceptionTypes.length - 1)
                        System.out.print(exceptionTypes[i].getName());
                    else
                        System.out.print(exceptionTypes[i].getName() + ",");
                }
            }

            System.out.println("{}");
            System.out.println();
        }
    }

    @Test
    public void methodTest3()
    {
        Class<Person> personClass = Person.class;

        //��ȡ��ǰ����ʱ��������Ϊpublic�Ĺ�����
        Constructor<?>[] constructors = personClass.getConstructors();
        for(Constructor<?> c : constructors)
        {
            System.out.println(c);
        }
        System.out.println();

        //��ȡ��ǰ����ʱ�������������еĹ�����
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for(Constructor<?> c : declaredConstructors)
        {
            System.out.println(c);
        }
        System.out.println();

        //��ȡ����ʱ��ĸ���
        Class<?> superclass = personClass.getSuperclass();
        System.out.println(superclass.getName());

        //��ȡ����ʱ��Ĵ����͵ĸ���
        Type genericSuperclass1 = personClass.getGenericSuperclass();
        System.out.println(genericSuperclass1);
        System.out.println();

        //��ȡ����ʱ��Ĵ����͵ĸ���ķ���
        Type genericSuperclass2 = personClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass2;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for(Type t : actualTypeArguments)
        {
            System.out.println(t);
        }
        System.out.println();

        //��ȡ����ʱ��ʵ�ֵĽӿ�
        Class<?>[] interfaces = personClass.getInterfaces();
        for(Class<?> c : interfaces)
        {
            System.out.println(c);
        }
        System.out.println();

        //��ȡ����ʱ�����ڵİ�
        System.out.println(personClass.getPackage());
        System.out.println();

        //��ȡ����ʱ��������ע��
        Annotation[] annotations = personClass.getAnnotations();
        for(Annotation a : annotations)
        {
            System.out.println(a);
        }
    }

    @Test
    public void specifiedTest() throws Exception
    {
        Class<Person> personClass = Person.class;
        //��������ʱ��Ķ���
        Person p = personClass.getDeclaredConstructor().newInstance();
        //getField()����ֻ�ܻ�ȡpublic�����ԣ������Խϴ󣬲��Ƽ�ʹ��
        //��ȡ����ʱ����ָ��������������
        Field name = personClass.getDeclaredField("name");
        //������������Ϊ�ɷ��ʵ�
        name.setAccessible(true);
        //��ȡ������ָ������Ĵ�����ֵ
        name.set(p,"PresidentXi");
        System.out.println(name.get(p));

        //��ȡstatic����
        Field age = personClass.getDeclaredField("age");
        age.setAccessible(true);
        //��ʱ��һ��ʵ�ο�������д��Ϊ�˱������һ��д��Person.class
        age.set(Person.class,67);
        //ͬ��
        System.out.println(age.get(Person.class));
    }

    @Test
    public void specifiedTest1() throws Exception
    {
        Class<Person> personClass = Person.class;
        //��������ʱ��Ķ���
        Person p = personClass.getDeclaredConstructor().newInstance();
        //��ȡָ������
        Method showInterests = personClass.getDeclaredMethod("showInterests", String.class, int.class);
        //���÷�������Ϊ�ɷ��ʵ�
        showInterests.setAccessible(true);
        //ʹ��invoke()���ø÷�����invoke()�ķ���ֵ���÷����ķ���ֵ
        //���÷���û�з���ֵ����invoke()����null
        String playingEldenRing = (String) showInterests.invoke(p, "PlayingEldenRing", 24);
        System.out.println(playingEldenRing);
        //���þ�̬����ͬ��
    }

    @Test
    public void specifiedTest2() throws Exception
    {
        Class<Person> personClass = Person.class;
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);
        Person p = declaredConstructor.newInstance("PresidentXi", 67);
        System.out.println(p);
    }

}


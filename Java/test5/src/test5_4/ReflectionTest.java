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
        //方式一：调用运行时类的属性：.class
        Class c1 = Person.class;

        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class c2 = p1.getClass();

        //方式三：调用Class的静态方法：forName(String classPath)
        Class c3 = Class.forName("test5_4.Person");//写目前不存在的类也不会报错

        //方式四：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class c4 = classLoader.loadClass("test5_4.Person");//同上

        //运行时类在内存中只会加载一份，因此获取的同一个类的Class实例都指向同一块内存空间
        System.out.println(c1 == c2 && c2 == c3 && c3 == c4);//true
    }

    @Test
    public void test2() throws IOException
    {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader1 = ReflectionTest.class.getClassLoader();
        System.out.println(classLoader1);
        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);//null

        //读取配置文件的方式：使用ClassLoader
        ClassLoader classLoader4 = ReflectionTest.class.getClassLoader();
        //配置文件默认位置为：当前module的src下
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
        //创建类的对象

        //方式一：调用指定构造器，生成Constructor的实例，用该实例生成类的对象，并初始化属性
        Constructor<Person> personConstructor = personClass.getConstructor(String.class,int.class);
        Person person1 = personConstructor.newInstance("习近平",67);

        //方式二：直接使用Class实例创建对象（要求该类必须有空参构造器）（该方法从JDK9就过时了）
        Person person2 = personClass.newInstance();
    }

    @Test
    public void fieldTest1()
    {
        Class<Person> personClass = Person.class;
        //获取类的属性
        //1.获取该类及其父类中声明为public访问权限的属性
        Field[] fields = personClass.getFields();
        for(Field f : fields)
        {
            System.out.println(f);
        }
        System.out.println();

        //2.获取该类（不包含父类）中声明的所有属性。（包括private）
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
        //获取属性的结构
        for(Field f : declaredFields)
        {
            //1.权限修饰符
            //返回的int值对应着一种权限修饰符，可用toString静态方法还原成对应的字符串
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + " ");

            //2.数据类型
            Class type = f.getType();
            System.out.print(type + " ");

            //3.变量名
            String name = f.getName();
            System.out.print(name + " ");

            System.out.println();
        }
    }

    @Test
    public void methodTest1()
    {
        Class<Person> personClass = Person.class;

        //获取当前运行时类及其所有父类中权限为public的方法
        Method[] methods = personClass.getMethods();
        for(Method m : methods)
        {
            System.out.println(m);
        }
        System.out.println();

        //获取当前运行时类（不包含父类）中声明的所有方法。（包括private）
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

        //获取方法的结构
        for(Method m : methods)
        {
            //获取方法的所有注释
            Annotation[] annotations = m.getAnnotations();
            for(Annotation a : annotations)
            {
                System.out.println(a);
            }

            //获取方法的权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + " ");

            //获取方法的返回值类型
            System.out.print(m.getReturnType() + " ");

            //获取方法的方法名
            System.out.print(m.getName() + " ");

            //获取方法的所有形参
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

            //获取方法抛出的的所有异常
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

        //获取当前运行时类中声明为public的构造器
        Constructor<?>[] constructors = personClass.getConstructors();
        for(Constructor<?> c : constructors)
        {
            System.out.println(c);
        }
        System.out.println();

        //获取当前运行时类中声明的所有的构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for(Constructor<?> c : declaredConstructors)
        {
            System.out.println(c);
        }
        System.out.println();

        //获取运行时类的父类
        Class<?> superclass = personClass.getSuperclass();
        System.out.println(superclass.getName());

        //获取运行时类的带泛型的父类
        Type genericSuperclass1 = personClass.getGenericSuperclass();
        System.out.println(genericSuperclass1);
        System.out.println();

        //获取运行时类的带泛型的父类的泛型
        Type genericSuperclass2 = personClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass2;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for(Type t : actualTypeArguments)
        {
            System.out.println(t);
        }
        System.out.println();

        //获取运行时类实现的接口
        Class<?>[] interfaces = personClass.getInterfaces();
        for(Class<?> c : interfaces)
        {
            System.out.println(c);
        }
        System.out.println();

        //获取运行时类所在的包
        System.out.println(personClass.getPackage());
        System.out.println();

        //获取运行时类声明的注解
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
        //创建运行时类的对象
        Person p = personClass.getDeclaredConstructor().newInstance();
        //getField()方法只能获取public的属性，局限性较大，不推荐使用
        //获取运行时类中指定变量名的属性
        Field name = personClass.getDeclaredField("name");
        //将该属性设置为可访问的
        name.setAccessible(true);
        //获取、设置指定对象的此属性值
        name.set(p,"PresidentXi");
        System.out.println(name.get(p));

        //获取static属性
        Field age = personClass.getDeclaredField("age");
        age.setAccessible(true);
        //此时第一个实参可随意填写，为了便于理解一般写成Person.class
        age.set(Person.class,67);
        //同上
        System.out.println(age.get(Person.class));
    }

    @Test
    public void specifiedTest1() throws Exception
    {
        Class<Person> personClass = Person.class;
        //创建运行时类的对象
        Person p = personClass.getDeclaredConstructor().newInstance();
        //获取指定方法
        Method showInterests = personClass.getDeclaredMethod("showInterests", String.class, int.class);
        //将该方法设置为可访问的
        showInterests.setAccessible(true);
        //使用invoke()调用该方法，invoke()的返回值即该方法的返回值
        //若该方法没有返回值，则invoke()返回null
        String playingEldenRing = (String) showInterests.invoke(p, "PlayingEldenRing", 24);
        System.out.println(playingEldenRing);
        //调用静态方法同上
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


package test5_3;

import org.junit.jupiter.api.Test;

import java.io.*;

public class OtherTest
{

    public static void main(String[] args)
    {
        BufferedReader bufferedReader = null;
        try
        {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);//System.out不用造对象
            bufferedReader = new BufferedReader(inputStreamReader);
            while(true)
            {
                System.out.print("输入：");
                String data = bufferedReader.readLine();
                if("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data))
                {
                    System.out.println("退出");
                    break;
                }
                String upperData = data.toUpperCase();
                System.out.print("输出：");
                System.out.println(upperData);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() throws FileNotFoundException, UnsupportedEncodingException
    {
        //本段代码省略了try-catch-finally结构
        FileOutputStream fileOutputStream = new FileOutputStream(new File("data.txt"));
        //创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)，设置编码格式
        PrintStream printStream = new PrintStream(fileOutputStream,true,"GBK");
        if(printStream != null)
        {
            //把标准输出流（控制台）改成"data.txt"文件
            System.setOut(printStream);
        }
        //输出ASCII字符
        for(int i = 0; i<255; i++)
        {
            System.out.print((char)i+" ");
            if(i % 50 == 0)
            {
                System.out.println();
            }
        }
        printStream.close();
    }

    // 以下代码均省略try-catch-finally结构
    @Test
    public void test3() throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File("dataNew.txt")));
        dataOutputStream.writeUTF("习近平");
        dataOutputStream.writeInt(24);
        dataOutputStream.writeLong(114514);
        dataOutputStream.close();
    }

    @Test
    public void test4() throws IOException
    {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File("dataNew.txt")));
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readInt());
        System.out.println(dataInputStream.readLong());
        dataInputStream.close();
    }

    @Test
    public void test5() throws IOException
    {
        //序列化
        ObjectOutputStream objectOutputStream = null;
        try
        {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("object.dat")));
            objectOutputStream.writeObject(new Person("习近平",69));
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(objectOutputStream != null)
                {
                    objectOutputStream.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test6()
    {
        //反序列化
        ObjectInputStream objectInputStream = null;
        Person person = null;
        try
        {
            objectInputStream = new ObjectInputStream(new FileInputStream(new File("object.dat")));
            person = (Person) objectInputStream.readObject();
            System.out.println(person);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(objectInputStream != null)
                {
                    objectInputStream.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Person implements Serializable
{
    private static final long serialVersionUID = 28347823991238L;
    String name;
    int age;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

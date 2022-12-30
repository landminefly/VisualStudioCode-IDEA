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
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);//System.out���������
            bufferedReader = new BufferedReader(inputStreamReader);
            while(true)
            {
                System.out.print("���룺");
                String data = bufferedReader.readLine();
                if("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data))
                {
                    System.out.println("�˳�");
                    break;
                }
                String upperData = data.toUpperCase();
                System.out.print("�����");
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
        //���δ���ʡ����try-catch-finally�ṹ
        FileOutputStream fileOutputStream = new FileOutputStream(new File("data.txt"));
        //������ӡ�����,����Ϊ�Զ�ˢ��ģʽ(д�뻻�з����ֽ� '\n' ʱ����ˢ�����������)�����ñ����ʽ
        PrintStream printStream = new PrintStream(fileOutputStream,true,"GBK");
        if(printStream != null)
        {
            //�ѱ�׼�����������̨���ĳ�"data.txt"�ļ�
            System.setOut(printStream);
        }
        //���ASCII�ַ�
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

    // ���´����ʡ��try-catch-finally�ṹ
    @Test
    public void test3() throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File("dataNew.txt")));
        dataOutputStream.writeUTF("ϰ��ƽ");
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
        //���л�
        ObjectOutputStream objectOutputStream = null;
        try
        {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("object.dat")));
            objectOutputStream.writeObject(new Person("ϰ��ƽ",69));
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
        //�����л�
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
